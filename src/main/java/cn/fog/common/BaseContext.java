package cn.fog.common;

/**
 * 基于ThreadLocal封装工具类，用来用户保存获取当前的用户id
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 设置值
     * @param id
     */
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    /**
     * 获取值
     * @return
     */
    public static Long getCurrentUId(){
        return threadLocal.get();

    }
}
