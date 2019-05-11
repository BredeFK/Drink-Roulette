package no.fritjof.drinkroulette;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ScanItemActivity extends AppCompatActivity {
    private static final int REQUEST_HIGH_QUALITY_IMAGE = 1;
    private String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_item);

    }

    private void highQualityImage() {
        Intent hqIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (hqIntent.resolveActivity(getPackageManager()) != null) {
            File file = null;
            try {
                file = createImageFile();
            } catch (IOException e) {
                e.getStackTrace();
            }
            if (file != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "no.fritjof.android.fileprovider",
                        file);
                hqIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(hqIntent, REQUEST_HIGH_QUALITY_IMAGE);
            }
        }
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",    /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_HIGH_QUALITY_IMAGE:
                    String barcode = getRawValueFromBarcode(getPicture());
                    if (!barcode.isEmpty()) {
                        searchEntry.setText(barcode);
                        imageView.setImageDrawable(null);
                        searchStoresForProducts(stores, barcode);
                    } else {
                        Toast.makeText(getBaseContext(), "Could not get barcode!", Toast.LENGTH_LONG).show();
                    }

                    break;
                default:
                    break;
            }
        }

    }

    private Bitmap getPicture() {
        int targetWidth = imageView.getWidth();
        int targetHeight = imageView.getHeight();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(currentPhotoPath, options);
        int photoWidth = options.outWidth;
        int photoHeight = options.outHeight;

        int scale = Math.min(photoWidth / targetWidth, photoHeight / targetHeight);

        options.inJustDecodeBounds = false;
        options.inSampleSize = scale;

        return BitmapFactory.decodeFile(currentPhotoPath, options);
    }


    private String getRawValueFromBarcode(Bitmap image) {
        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(this).build();

        Frame frame = new Frame.Builder().setBitmap(image).build();
        SparseArray<Barcode> barcodes = barcodeDetector.detect(frame);
        if (barcodes.size() > 0) {
            return barcodes.valueAt(0).rawValue;
        }

        return "";
    }
    */


}
