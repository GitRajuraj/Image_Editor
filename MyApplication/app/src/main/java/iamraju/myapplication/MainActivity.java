package iamraju.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;


public class MainActivity extends Activity {

    static ImageView imageView;
    SeekBar mybar;
    Bitmap bm;
    private static int RESULT_LOAD_IMAGE = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mybar = (SeekBar) findViewById(R.id.seekBar1);

        mybar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                //add here your implementation
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                //add here your implementation
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {


                ColorFilter filter1 = new LightingColorFilter(Color.WHITE, Color.BLUE);
                ColorFilter filter2 = new LightingColorFilter(Color.GREEN, Color.BLACK);
                ColorFilter filter3 = new LightingColorFilter(Color.RED, Color.BLACK);


                if (imageView != null) {

                    if (progress >=0 && progress < 25) {
                        imageView.setColorFilter(0);
                        imageView.buildDrawingCache();
                        bm = imageView.getDrawingCache();
                    }
                    if (progress >=25 && progress < 50) {
                        imageView.setColorFilter(filter1);
                        imageView.buildDrawingCache();
                        bm = imageView.getDrawingCache();
                    }

                    if (progress >= 50 && progress < 75) {
                        imageView.setColorFilter(filter2);
                        imageView.buildDrawingCache();
                        bm = imageView.getDrawingCache();
                    }
                    if (progress >= 75 && progress < 100) {
                        imageView.setColorFilter(filter3);
                        imageView.buildDrawingCache();
                        bm = imageView.getDrawingCache();
                    }
                }
            }
        });


        Button buttonLoadImage = (Button) findViewById(R.id.buttonLoadPicture);


        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });



        Button SaveImage = (Button) findViewById(R.id.Save_Picture);

        SaveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SaveImage(bm);

            }
        });




    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            imageView = (ImageView) findViewById(R.id.imgView);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }


    }

    private void SaveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-"+ n +".jpg";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}