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
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends Activity {

    static ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,main_imageView;
    private static int RESULT_LOAD_IMAGE = 1;
    ColorFilter filter2 = new LightingColorFilter(Color.GREEN, Color.BLACK);
    ColorFilter filter3 = new LightingColorFilter(Color.argb(0,0,0,0), Color.BLACK);
    ColorFilter filter4 = new LightingColorFilter(Color.GRAY, Color.BLACK);
    ColorFilter filter5 = new LightingColorFilter(Color.argb(255,230,170,90), Color.BLACK);
    ColorFilter filter6 = new LightingColorFilter(Color.argb(255,150,100,80), Color.BLACK);
    ColorFilter filter7 = new LightingColorFilter(Color.argb(255,80,200,210), Color.BLACK);
    ColorFilter filter8 = new LightingColorFilter(Color.argb(255,220,100,80), Color.BLACK);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
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

            main_imageView= (ImageView) findViewById(R.id.Main_imgView);

            imageView1 = (ImageView) findViewById(R.id.imgView1);
            imageView2 = (ImageView) findViewById(R.id.imgView2);
            imageView3 = (ImageView) findViewById(R.id.imgView3);
            imageView4 = (ImageView) findViewById(R.id.imgView4);
            imageView5 = (ImageView) findViewById(R.id.imgView5);
            imageView6 = (ImageView) findViewById(R.id.imgView6);
            imageView7 = (ImageView) findViewById(R.id.imgView7);
            imageView8 = (ImageView) findViewById(R.id.imgView8);


            Bitmap bitmapImage = BitmapFactory.decodeFile(picturePath);

            final  Bitmap scaled = Bitmap.createScaledBitmap(bitmapImage, bitmapImage.getWidth(), bitmapImage.getHeight(), true);
            main_imageView.setImageBitmap(scaled);

            final Bitmap scaled_small = Bitmap.createScaledBitmap(bitmapImage, 100, 100, true);

            imageView1.setImageBitmap(scaled_small);
            imageView2.setImageBitmap(scaled_small);
            imageView3.setImageBitmap(scaled_small);
            imageView4.setImageBitmap(scaled_small);
            imageView5.setImageBitmap(scaled_small);
            imageView6.setImageBitmap(scaled_small);
            imageView7.setImageBitmap(scaled_small);
            imageView8.setImageBitmap(scaled_small);

            imageView2.setColorFilter(filter2);
            imageView3.setColorFilter(filter3);
            imageView4.setColorFilter(filter4);
            imageView5.setColorFilter(filter5);
            imageView6.setColorFilter(filter6);
            imageView7.setColorFilter(filter7);
            imageView8.setColorFilter(filter8);

            imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    main_imageView.setColorFilter(0);
                }
            });

            imageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    main_imageView.setColorFilter(filter2);

                }
            });

            imageView3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    main_imageView.setColorFilter(filter3);

                }
            });

            imageView4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    main_imageView.setColorFilter(filter4);

                }
            });

            imageView5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    main_imageView.setColorFilter(filter5);

                }
            });

            imageView6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    main_imageView.setColorFilter(filter6);

                }
            });

            imageView7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    main_imageView.setColorFilter(filter7);

                }
            });
            imageView8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    main_imageView.setColorFilter(filter8);

                }
            });

        }}

    public static Bitmap createContrast(Bitmap src, double value) {
// image size
        int width = src.getWidth();
        int height = src.getHeight();
// create output bitmap
        Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
// color information
        int A, R, G, B;
        int pixel;
// get contrast value
        double contrast = Math.pow((100 + value) / 100, 2);

// scan through all pixels
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                // get pixel color
                pixel = src.getPixel(x, y);
                A = Color.alpha(pixel);
                // apply filter contrast for every channel R, G, B
                R = Color.red(pixel);
                R = (int)(((((R / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
                if(R < 0) { R = 0; }
                else if(R > 255) { R = 255; }

                G = Color.red(pixel);
                G = (int)(((((G / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
                if(G < 0) { G = 0; }
                else if(G > 255) { G = 255; }

                B = Color.red(pixel);
                B = (int)(((((B / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
                if(B < 0) { B = 0; }
                else if(B > 255) { B = 255; }

                // set new pixel color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }

        return bmOut;
    }

}