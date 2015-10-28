package com.yul.androidpermissiontest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            /*    */
                int status = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);
                if (status == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 1001);
                } else {
                    if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.CAMERA)){
                        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission
                                .CAMERA}, 0);
                    }
                    else {
                         Snackbar.make(view, "请在设置中打开相机权限，否则无法使用该功能！", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                    }
                }

            /*    if (Build.VERSION.SDK_INT >= 23) {
                    int status = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);
                    switch (status) {
                        case PackageManager.PERMISSION_GRANTED:

                            break;
                        default:
                            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest
                                    .permission.CAMERA)) {
                                requestPermissions(new String[]{Manifest.permission
                                        .CAMERA}, 0);
                            } else {

                            }

                            break;
                    }
                }*/
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 0:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 1001);

                } else {
                    //Tips
                }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
/*        Log.e(TAG, "requestCode=" + requestCode + "resultCode=" + resultCode + "data.getData==null?" + (data.getData
                () == null));*/
        if (resultCode == RESULT_OK) {
            if (data.getData() != null) {
                Log.e(TAG, data.getData().toString());
            } else {
                Bundle bundle = data.getExtras();
                if (null != bundle) {
                    //Bitmap bitmap= (Bitmap) bundle.get("data");
                    //Log.e(TAG,bitmap.);
                }

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
