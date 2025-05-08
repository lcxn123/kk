package four_;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OHRequest {
    public String description;
    public String name;
    public boolean isSetup;
    public OHRequest next;
    public OHRequest(String description, String name, boolean isSetup, OHRequest next) {
        this.description = description;
        this.name = name;
        this.isSetup = isSetup;
        this.next = next;
    }


    public class OHIterator implements Iterator<OHRequest> {
        private OHRequest curr;

        public static boolean isGood(String description){return description.length() >= 5;}

        public OHIterator(OHRequest request){
            curr = request;
        }
        @Override
        public boolean hasNext() {
            while(curr != null && !isGood(curr.description)){
                curr = curr.next;
            }
            return curr.next != null;
        }

        @Override
        public OHRequest next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            OHRequest temp = curr;
            curr = curr.next;
            return temp;
        }
    }

    public class OHQueue implements Iterable<OHRequest>{
        private OHRequest request;

        public OHQueue(OHRequest request){
            this.request = request;
        }

        @Override
        public Iterator<OHRequest> iterator() {
            return new OHIterator(request);
        }
    }

    public class TYIterator extends OHIterator{

        public TYIterator(OHRequest request) {
            super(request);
        }

        @Override
        public OHRequest next(){
            OHRequest result = super.next();
            if (result.description.contains("thank u")){
                super.next();
            }
            return result;
        }
    }


}
