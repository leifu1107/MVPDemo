package leifu.mvpdemo.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;

import butterknife.BindView;
import leifu.mvpdemo.R;
import leifu.mvpdemo.base.BaseActivity;
import leifu.mvpdemo.model.bean.BaseBean;
import leifu.mvpdemo.presenter.UpPicPresenter;
import leifu.mvpdemo.presenter.contract.UpPicContract;
import leifu.mvpdemo.utils.GlideImgManager;
import leifu.mvpdemo.utils.Logger;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/30 14:52
 * 描述:
 */

public class UpPicActivity extends BaseActivity<UpPicPresenter> implements UpPicContract.View, TakePhoto.TakeResultListener, InvokeListener {
    @BindView(R.id.ivUp)
    ImageView ivUp;
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_bus;
    }

    @Override
    protected void initEventAndData() {
        ivUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
                Uri imageUri = Uri.fromFile(file);
                CropOptions cropOptions = new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(true).create();
                CompressConfig compressConfig = new CompressConfig.Builder().setMaxSize(10 * 1024).setMaxPixel(50).create();
                takePhoto.onEnableCompress(compressConfig, true);
                getTakePhoto().onPickFromDocumentsWithCrop(imageUri, cropOptions);
            }
        });
    }

    @Override
    public void showContent(BaseBean baseBean) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    @Override
    public void takeSuccess(TResult result) {
        Logger.e("aaa" + "takeSuccess：" + result.getImage().getOriginalPath());
        GlideImgManager.loadImage(mContext, new File(result.getImage().getCompressPath()), ivUp);
        File file = new File(result.getImage().getCompressPath());
        // create RequestBody instance from file
        RequestBody meetId = RequestBody.create(MediaType.parse("text/plain"), "1");
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        mPresenter.getUpPic(meetId, body);
    }

    @Override
    public void takeFail(TResult result, String msg) {
        Logger.e("aaa" + "takeFail：" + msg);
    }

    @Override
    public void takeCancel() {
        Logger.e("aaa" + "takeCancel：" + getResources().getString(R.string.msg_operation_canceled));
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }
}
