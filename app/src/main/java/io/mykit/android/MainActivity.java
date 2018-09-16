package io.mykit.android;

import android.content.pm.IPackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.lang.reflect.Method;

/**
 * @author liuyazhuang
 * @date 2018-09-16
 * @version 1.0.0
 * @description  Android 静默安装实现
 */
public class MainActivity extends AppCompatActivity {

    private File sdCardFile = Environment.getExternalStorageDirectory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pmInstall(View view){
        File apkFile = new File(sdCardFile, "target.apk");
        try {
            Class<?> clazz = Class.forName("android.os.ServiceManager");
            Method method_getService = clazz.getMethod("getService",
                    String.class);
            IBinder bind = (IBinder) method_getService.invoke(null, "package");

            IPackageManager iPm = IPackageManager.Stub.asInterface(bind);
            iPm.installPackage(Uri.fromFile(apkFile), null, 2, apkFile.getName());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
