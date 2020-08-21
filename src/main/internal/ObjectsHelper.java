package internal;

import guavaUtils.annotations.VisibleForTesting;

public final class ObjectsHelper {

    private ObjectsHelper(){
        //prevents instantiation
    }

    @VisibleForTesting
    static void instantiateForTestCoveragePurposesOnly(){
        new ObjectsHelper();
    }

    @SuppressWarnings("unchecked")
    public static <T> T asClass(Object object, Class<T> cls){
        if(object == null){
            return null;
        } else if(object.getClass() != cls){
            return null;
        } else{
            return (T) object;
        }
    }
}
