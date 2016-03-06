package com.example.grunert.colorchanger;

// http://cdn.tutsplus.com/active/uploads/legacy/tuts/070_effectsTester/Preview/EffectsTester.html
// http://android-er.blogspot.it/2015/10/android-example-code-using-colorfilter.html

import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    SeekBar redBar, greenBar, blueBar, angelBar;
    TextView redText, greenText, blueText, angelText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.iv);
        redBar = (SeekBar)findViewById(R.id.redbar);
        greenBar = (SeekBar)findViewById(R.id.greenbar);
        blueBar = (SeekBar)findViewById(R.id.bluebar);
        angelBar = (SeekBar)findViewById(R.id.angelbar);

        redText = (TextView)findViewById(R.id.tvr);
        greenText = (TextView)findViewById(R.id.tvg);
        blueText = (TextView)findViewById(R.id.tvb);
        angelText = (TextView)findViewById(R.id.angleText);

        redBar.setOnSeekBarChangeListener(colorBarChangeListener);
        greenBar.setOnSeekBarChangeListener(colorBarChangeListener);
        blueBar.setOnSeekBarChangeListener(colorBarChangeListener);

        angelBar.setOnSeekBarChangeListener(angelBarChangeListener);

        setColorFilter(imageView);
    }

    SeekBar.OnSeekBarChangeListener colorBarChangeListener = new SeekBar.OnSeekBarChangeListener(){

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            setColorFilter(imageView);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    SeekBar.OnSeekBarChangeListener angelBarChangeListener = new SeekBar.OnSeekBarChangeListener(){
        int angel;

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            angel = Math.abs(progress - 180);
            angelText.setText(String.format("%d %.4f %.4f",angel,(float)angel*255f/180f, (float)angel*-255f/180f+255f));
            setColorFilterfromAngel(imageView);

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void setColorFilterfromAngel(ImageView iv){
        int angel;
        angel = Math.abs(angelBar.getProgress() - 180);


       /*
        * 5x4 matrix for transforming the color+alpha components of a Bitmap.
        * The matrix is stored in a single array, and its treated as follows:
        * [  a, b, c, d, e,
        *   f, g, h, i, j,
        *   k, l, m, n, o,
        *   p, q, r, s, t ]
        *
        * When applied to a color [r, g, b, a], the resulting color is computed
        * as (after clamping)
        * R' = a*R + b*G + c*B + d*A + e;
        * G' = f*R + g*G + h*B + i*A + j;
        * B' = k*R + l*G + m*B + n*A + o;
        * A' = p*R + q*G + r*B + s*A + t;
        */

        float redValue = ((float)(float)angel*255f/180f)/255;
        float greenValue = ((float)angel*-255f/180f+255f)/255;
        float blueValue = ((float)blueBar.getProgress())/255;

        redText.setText(String.format("Red: %.6f",redValue));
        greenText.setText(String.format("Green: %.6f",greenValue));
        blueText.setText(String.format("Blue: %.6f",blueValue));

        float[] colorMatrix = {
                redValue, 0, 0, 0, 0,  //red
                0, greenValue, 0, 0, 0, //green
                0, 0, blueValue, 0, 0,  //blue
                0, 0, 0, 1, 0    //alpha
        };

        ColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
        iv.setColorFilter(colorFilter);
    }


    private void setColorFilter(ImageView iv){

       /*
        * 5x4 matrix for transforming the color+alpha components of a Bitmap.
        * The matrix is stored in a single array, and its treated as follows:
        * [  a, b, c, d, e,
        *   f, g, h, i, j,
        *   k, l, m, n, o,
        *   p, q, r, s, t ]
        *
        * When applied to a color [r, g, b, a], the resulting color is computed
        * as (after clamping)
        * R' = a*R + b*G + c*B + d*A + e;
        * G' = f*R + g*G + h*B + i*A + j;
        * B' = k*R + l*G + m*B + n*A + o;
        * A' = p*R + q*G + r*B + s*A + t;
        */

        float redValue = ((float)redBar.getProgress())/255;
        float greenValue = ((float)greenBar.getProgress())/255;
        float blueValue = ((float)blueBar.getProgress())/255;

        redText.setText(String.format("Red: %.6f",redValue));
        greenText.setText(String.format("Green: %.6f",greenValue));
        blueText.setText(String.format("Blue: %.6f",blueValue));

        float[] colorMatrix = {
                redValue, 0, 0, 0, 0,  //red
                0, greenValue, 0, 0, 0, //green
                0, 0, blueValue, 0, 0,  //blue
                0, 0, 0, 1, 0    //alpha
        };

        ColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
        iv.setColorFilter(colorFilter);
    }
}
