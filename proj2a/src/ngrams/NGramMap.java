package ngrams;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    // TODO: Add any necessary static/instance variables.
    private HashMap wordMap;
    private TimeSeries countsTime;

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        // TODO: Fill in this constructor. See the "NGramMap Tips" section of the spec for help.
        // 初始化两个存储结构
        wordMap = new HashMap<String,TimeSeries>();
        countsTime = new TimeSeries();

        // 储存解析后的数据
        List<String[]> wordList = new ArrayList<>();
        List<String[]> countList = new ArrayList<>();

        // 开始解析文件
        try (BufferedReader br = new BufferedReader(new FileReader(wordsFilename))){
            String line;
            while((line = br.readLine()) != null){
                // 制表符分隔
                String[] values = line.split("\t");

                //  储存数据
                wordList.add(values);
            }
        } catch (IOException a){
            a.printStackTrace();
        }


        try (BufferedReader br = new BufferedReader(new FileReader(countsFilename))){
            String line;
            while((line = br.readLine()) != null){
                // 逗号分隔
                String[] values = line.split(",");

                //  储存数据
                countList.add(values);
            }
        } catch (IOException a){
            a.printStackTrace();
        }

        // 将存储的数据转化到准备好的数据结构中
        for (int i = 0; i < wordList.size(); i++) {
            String word = wordList.get(i)[0];
            int year = Integer.parseInt(wordList.get(i)[1]);
            double count = Double.parseDouble(wordList.get(i)[2]);


            TimeSeries time = new TimeSeries();
            time.put(year,count);

            // 检查是否有相同的key

            if (wordMap.containsKey(word)){
                time = time.plus((TimeSeries) wordMap.get(word));
            }
            wordMap.put(word,time);
        }

        for (int i = 0; i < countList.size(); i++) {
            int year = Integer.parseInt(countList.get(i)[0]);
            double count = Double.parseDouble(countList.get(i)[1]);

            countsTime.put(year,count);
        }

    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        if (!wordMap.containsKey(word)){return null;}
        TimeSeries time = (TimeSeries) wordMap.get(word);
        TimeSeries resTime = new TimeSeries(time,startYear,endYear);
        return resTime;
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        // TODO: Fill in this method.
        TimeSeries time = (TimeSeries) wordMap.get(word);
        return time;
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        // TODO: Fill in this method.
        TimeSeries copyTime = new TimeSeries();
        copyTime = countsTime.plus(copyTime);
        return copyTime;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        if (!wordMap.containsKey(word)){
            return new TimeSeries();
        }

        TimeSeries time = (TimeSeries) wordMap.get(word);
        time = new TimeSeries(time,startYear,endYear);

        TimeSeries ts = new TimeSeries(countsTime,startYear,endYear);

        time = time.dividedBy(ts);
        return time;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        // TODO: Fill in this method.
        if (!wordMap.containsKey(word)){
            return new TimeSeries();
        }

        TimeSeries time = (TimeSeries) wordMap.get(word);

        TimeSeries ts = new TimeSeries();
        ts = ts.plus(countsTime);

        time = time.dividedBy(ts);
        return time;
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words, int startYear, int endYear) {
        // 检查输入参数的有效性
        if (words == null || words.isEmpty()) {
            throw new IllegalArgumentException("Words collection cannot be null or empty.");
        }
        if (startYear > endYear) {
            throw new IllegalArgumentException("Start year must be less than or equal to end year.");
        }

        // 初始化结果 TimeSeries
        TimeSeries ts = new TimeSeries();

        // 遍历所有单词，累加权重
        for (String word : words) {
            if (!wordMap.containsKey(word)) {
                continue;
            }
            TimeSeries time = (TimeSeries) wordMap.get(word);
            if (time == null) {
                continue;
            }
            ts = ts.plus(time);
        }

        // 限制年份范围
        ts = new TimeSeries(ts, startYear, endYear);

        // 创建用于除法的 TimeSeries
        TimeSeries dividedTs = new TimeSeries(countsTime, startYear, endYear);

        // 检查除数是否为空或包含零值
        if (dividedTs.isEmpty()) {
            throw new IllegalArgumentException("Divisor TimeSeries is empty.");
        }


        // 执行除法操作
        ts = ts.dividedBy(dividedTs);

        return ts;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: Fill in this method.
        TimeSeries ts = new TimeSeries();

        // sum
        for(String word : words){
            TimeSeries time = (TimeSeries) wordMap.get(word);
            ts = ts.plus(time);
        }



        // 创建一个被除的ts

        TimeSeries dividedTs = new TimeSeries();
        dividedTs = dividedTs.plus(countsTime);


        ts = ts.dividedBy(dividedTs);
        return ts;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
