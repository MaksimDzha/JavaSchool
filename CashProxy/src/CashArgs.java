import java.util.Set;
import java.util.TreeSet;

class CashArgs {

    static String get(Object[] args, Cache cacheAnn) {
        String cacheArgs = new String();
        Set<Integer> argsToCache = new TreeSet<>();
        for (Integer i : cacheAnn.args())
            if ((i > 0) && (i <= args.length)) argsToCache.add(i);
        if (args != null)
            if (!argsToCache.isEmpty())
                for (Integer i : argsToCache)
                    cacheArgs += args[i-1].toString() + " ";
            else
                for (Object arg : args)
                    cacheArgs += arg.toString() + " ";
        else
            cacheArgs = "";
        return cacheArgs;
    }
}
