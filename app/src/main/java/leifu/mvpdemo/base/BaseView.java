package leifu.mvpdemo.base;

/**
 * Created by codeest on 2016/8/2.
 * View基类
 */
public interface BaseView {

    void showErrorMsg(String msg);


    //=======  State  =======
    void stateError();
    void stateLoading();

    void goLogin();
}
