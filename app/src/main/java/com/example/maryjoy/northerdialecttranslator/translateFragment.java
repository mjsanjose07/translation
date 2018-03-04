package com.example.maryjoy.northerdialecttranslator;


import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import static android.R.color.black;
import static android.R.id.edit;
import static android.R.id.list;
import static android.content.Context.CLIPBOARD_SERVICE;


public class translateFragment extends Fragment {

    EditText editText;
    TextView resultText;
    Button btntranslate;

    ImageButton btnspeak;
    ImageButton imgbtn1;
    ImageButton imgbtn2;
    ImageButton imgbtn3;
    ImageButton imgbtn4;

    ImageView one;
    ImageView two;

    SpeechRecognizer mSpeechRecognizer;
    Intent mSpeechRecognizerIntent;

    TextToSpeech toSpeech;
    int result;
    int iDialect = 0;
    String text;


    ClipboardManager clipboardManager;
    ClipData clipData;

    Spinner spinner1;

    String[] english, waray, pangalatok, hiligaynon, ilocano;

    String [] dialectEnglish = {
            "WARAY", "HILIGAYNON", "PANGALATOK", "ILOCANO"
    };
    String [] dialectWaray = {
            "ENGLISH", "HILIGAYNON", "PANGALATOK", "ILOCANO"
    };

    String [] dialectHiligaynon = {
            "WARAY", "ENGLISH", "PANGALATOK", "ILOCANO"
    };

    String [] dialectPangalatok = {
            "WARAY", "HILIGAYNON", "ENGLISH", "ILOCANO"
    };

    String [] dialectIlocano = {
            "WARAY", "HILIGAYNON", "PANGALATOK", "ENGLISH"
    };

    ArrayAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_translate, container, false);
    }


    @Override
    public void onViewCreated(final View view, @Nullable Bundle saveIntanceState) {
        super.onViewCreated(view, saveIntanceState);

        editText = (EditText) getView().findViewById(R.id.editText);
        resultText = (TextView) getView().findViewById(R.id.resultText);
        btntranslate = (Button) getView().findViewById(R.id.btntranslate);

        btnspeak = (ImageButton) getView().findViewById(R.id.btnspeak);
        imgbtn1 = (ImageButton) getView().findViewById(R.id.imgbtn1);
        imgbtn2 = (ImageButton) getView().findViewById(R.id.imgbtn2);
        imgbtn3 = (ImageButton) getView().findViewById(R.id.imgbtn3);
        imgbtn4 = (ImageButton) getView().findViewById(R.id.imgbtn4);

        one = (ImageView) getView().findViewById(R.id.one);
        two = (ImageView) getView().findViewById(R.id.two);

        spinner1 = (Spinner) getView().findViewById(R.id.spinner1);
        spinner1.setEnabled(false);

        english = getResources().getStringArray(R.array.english);
        waray = getResources().getStringArray(R.array.waray);
        pangalatok = getResources().getStringArray(R.array.pangalatok);
        hiligaynon = getResources().getStringArray(R.array.hiligaynon);
        ilocano = getResources().getStringArray(R.array.ilocano);


        editText.addTextChangedListener(changeEdit);

        /*
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                editText.setText("");
                return false;
            }
        });
        */

        imgbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                phraseFragment Pf = new phraseFragment();
                String data = editText.getText().toString();
                String data1 = resultText.getText().toString();
                String data2 = spinner1.getSelectedItem().toString();

                Bundle bundle = new Bundle();
                bundle.putString("Words_Input", data);
                bundle.putString("Words_Translate", data1);
                bundle.putString("Words_dialect", data2);

                Pf.setArguments(bundle);

                FragmentManager mngr = getFragmentManager();
                mngr.beginTransaction().replace(R.id.mainLayout, Pf, Pf.getTag()).commit();


            }
        });

        btntranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (spinner1.getSelectedItem().toString()) {
                    case "English":
                        for (int x = 0; x != english.length; x++) {
                            if (editText.getText().toString().equals(waray[x])) {
                                resultText.setText(english[x]);

                            } else if (editText.getText().toString().equals(pangalatok[x])) {
                                resultText.setText(english[x]);

                            } else if (editText.getText().toString().equals(hiligaynon[x])) {
                                resultText.setText(english[x]);

                            } else if (editText.getText().toString().equals(ilocano[x])) {
                                resultText.setText(english[x]);

                            }

                        }
                        break;

                    case "Waray":
                        for (int x = 0; x != english.length; x++) {
                            if (editText.getText().toString().equals(english[x])) {
                                resultText.setText(waray[x]);
                            }
                        }
                        break;

                    case "Pangalatok":
                        for (int x = 0; x != english.length; x++) {
                            if (editText.getText().toString().equals(english[x])) {
                                resultText.setText(pangalatok[x]);
                            }
                        }
                        break;

                    case "Hiligaynon":
                        for (int x = 0; x != english.length; x++) {
                            if (editText.getText().toString().equals(english[x])) {
                                resultText.setText(hiligaynon[x]);
                            }
                        }
                        break;

                    case "Ilocano":
                        for (int x = 0; x != english.length; x++) {
                            if (editText.getText().toString().equals(english[x])) {
                                resultText.setText(ilocano[x]);
                            }
                        }
                        break;


                    default:
                        break;

                }
            }
        });


        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(getActivity());
        mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {

            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                if (matches != null)
                    editText.setText(matches.get(0));
            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });

        btnspeak.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {

                    case MotionEvent.ACTION_UP:
                        mSpeechRecognizer.stopListening();
                        break;

                    case MotionEvent.ACTION_DOWN:
                        editText.setText("");
                        editText.setHint("Speaking.....");
                        Toast.makeText(getActivity(), "Please speak!", Toast.LENGTH_SHORT).show();
                        mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
                        break;

                }
                return false;
            }
        });

        toSpeech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    result = toSpeech.setLanguage(Locale.ENGLISH);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Feature not supported in your device", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.imgbtn1:
                        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                            Toast.makeText(getActivity().getApplicationContext(), "Feature not supported in your device", Toast.LENGTH_SHORT).show();
                        } else {
                            text = resultText.getText().toString();
                            toSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                        }
                        break;
                }
            }
        });

        clipboardManager = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
        imgbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                clipData = ClipData.newPlainText("text", text);
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(getActivity().getApplicationContext(), "Text Copied!", Toast.LENGTH_SHORT).show();
            }
        });


        imgbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

            if (!(ContextCompat
                    .checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO)
                    == PackageManager.PERMISSION_GRANTED)) {

                Intent i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + getActivity().getPackageName()));
                startActivity(i);
                getActivity().finish();
            }
        }
    }



    public TextWatcher changeEdit = new TextWatcher() {

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(s.length() == 0){
                editText.setText("");
                spinner1.setEnabled(false);
            }else{
                spinner1.setEnabled(true);
                String temp = editText.getText().toString();
                for(int x = 0; x < english.length; x++){
                    if(temp.toLowerCase().equals(english[x])){
                        setSpinner(1, x);
                        break;
                    }else if(x<waray.length && temp.toLowerCase().equals(waray[x])){
                        setSpinner(2, x);
                        break;
                    }else if(x<hiligaynon.length && temp.toLowerCase().equals(hiligaynon[x])){
                        setSpinner(3, x);
                        break;
                    }else if(x<pangalatok.length && temp.toLowerCase().equals(pangalatok[x])){
                        setSpinner(4, x);
                        break;
                    }else if(x<ilocano.length && temp.toLowerCase().equals(ilocano[x])){
                        setSpinner(5, x);
                        break;
                    }
                }
            }
        }

        public void afterTextChanged(Editable s) {

        }
    };

    public void setSpinner(int ind, final int pos){

        switch(ind){
            case 1 :
                adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, dialectEnglish);
                break;
            case 2 :
                adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, dialectWaray);
                break;
            case 3 :
                adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, dialectHiligaynon);
                break;
            case 4 :
                adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, dialectPangalatok);
                break;
            case 5 :
                adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, dialectIlocano);
                break;
            default :
                break;
        }

        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch(adapter.getItem(i).toString()){
                    case "ENGLISH" :
                        Toast.makeText(getActivity().getApplicationContext(), english[pos], Toast.LENGTH_LONG).show();
                        break;
                    case "WARAY" :
                        Toast.makeText(getActivity().getApplicationContext(), waray[pos], Toast.LENGTH_LONG).show();
                        break;
                    case "HILIGAYNON" :
                        Toast.makeText(getActivity().getApplicationContext(), hiligaynon[pos], Toast.LENGTH_LONG).show();
                        break;
                    case "PANGALATOK" :
                        Toast.makeText(getActivity().getApplicationContext(), pangalatok[pos], Toast.LENGTH_LONG).show();
                        break;
                    case "ILOCANO" :
                        Toast.makeText(getActivity().getApplicationContext(), ilocano[pos], Toast.LENGTH_LONG).show();
                        break;
                    default :
                        break;
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

    }
}