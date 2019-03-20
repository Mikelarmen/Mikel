package com.example.rememberingreminding;

import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.JetPlayer;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.rememberingreminding.leff.midi.MidiFile;
import com.example.rememberingreminding.leff.midi.MidiTrack;
import com.example.rememberingreminding.leff.midi.event.ChannelEvent;
import com.example.rememberingreminding.leff.midi.event.MidiEvent;
import com.example.rememberingreminding.leff.midi.event.NoteOff;
import com.example.rememberingreminding.leff.midi.event.NoteOn;
import com.example.rememberingreminding.leff.midi.event.meta.KeySignature;
import com.example.rememberingreminding.leff.midi.event.meta.Lyrics;
import com.example.rememberingreminding.leff.midi.event.meta.Tempo;
import com.example.rememberingreminding.leff.midi.examples.EventPrinter;
import com.example.rememberingreminding.leff.midi.util.MidiProcessor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button bLogout, bPlay, bStop;

    TextView tvMidiText;

    Session session;

    MediaPlayer mediaPlayer;

    List<Double> freqTable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.file_midi);

        session = new Session(this);

        if(session.get("username")==""){
            Toast temp = Toast.makeText(MainActivity.this, "You are not logged in!", Toast.LENGTH_SHORT);
            temp.show();
            startActivity(new Intent(this, Login.class));
        }

        String username = session.get("username");

        tvMidiText = (TextView) findViewById(R.id.tvMidiText);
        bPlay = (Button) findViewById(R.id.bPlay);
        bStop = (Button) findViewById(R.id.bStop);
        bLogout = (Button) findViewById(R.id.bLogout);

        bPlay.setOnClickListener(this);
        bStop.setOnClickListener(this);
        bLogout.setOnClickListener(this);
    }

    @Override
    protected void onStart(){
        super.onStart();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogout:
                session.set("username", "");
                startActivity(new Intent(this, Login.class));
                mediaPlayer.pause();
                break;

            case R.id.bPlay:
                /*if(!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                }*/

                /*AudioTrack audioTrack = generateTone(1567.98, 500);

                audioTrack.play();*/


                // Midi fitxategiekin proba.
                try {
                    MidiFile midi = new MidiFile(getResources().openRawResource(R.raw.file_midi));

                    List<MidiTrack> tracks = midi.getTracks();

                    TreeSet<MidiEvent>  events = tracks.get(0).getEvents();

                    Iterator<MidiEvent> it = events.iterator();

                    List<Integer> noteValues = new ArrayList<Integer>();

                    while(it.hasNext()) {
                        MidiEvent nextEvent = it.next();
                        if(nextEvent.getClass().equals(NoteOn.class)) {
                            NoteOn noteOn = (NoteOn) nextEvent;
                            noteValues.add(noteOn.getNoteValue());
                        }
                    }

                    List<Double> notesFreq = toFrequencies(noteValues);
                    System.out.println(noteValues);
                    System.out.println(notesFreq);/*
                    for (int i = 0; i < notesFreq.size(); i++) {
                        generateTone(notesFreq.get(i), 500);
                    }*/

                    /*
                    // Create a new MidiProcessor:
                    MidiProcessor processor = new MidiProcessor(midi);

                    // Register for the events you're interested in:
                    EventPrinter ep = new EventPrinter("Individual Listener");
                    processor.registerEventListener(ep, NoteOn.class);

                    // or listen for all events:
                    EventPrinter ep = new EventPrinter("Listener For All");
                    processor.registerEventListener(ep, MidiEvent.class);


                    // Start the processor:
                    processor.start();*/
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

            case R.id.bStop:
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
                break;

        }
    }

    private AudioTrack generateTone(double freqHz, int durationMs)
    {
        int count = (int)(44100.0 * 2.0 * (durationMs / 1000.0)) & ~1;
        short[] samples = new short[count];
        for(int i = 0; i < count; i += 2){
            short sample = (short)(Math.sin(2 * Math.PI * i / (44100.0 / freqHz)) * 0x7FFF);
            samples[i + 0] = sample;
            samples[i + 1] = sample;
        }
        AudioTrack track = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT,
                count * (Short.SIZE / 8), AudioTrack.MODE_STATIC);
        track.write(samples, 0, count);
        return track;
    }

    private List<Double> toFrequencies(List<Integer> notes){
        List<Double> freqTable = new ArrayList<Double>();
        freqTable.add(8.18);
        freqTable.add(8.66);
        freqTable.add(9.18);
        freqTable.add(9.72);
        freqTable.add(10.30);
        freqTable.add(10.91);
        freqTable.add(11.56);
        freqTable.add(12.25);
        freqTable.add(12.98);
        freqTable.add(13.75);
        freqTable.add(14.57);
        freqTable.add(15.43);
        freqTable.add(16.35);
        freqTable.add(17.32);
        freqTable.add(18.35);
        freqTable.add(19.45);
        freqTable.add(20.60);
        freqTable.add(21.83);
        freqTable.add(23.12);
        freqTable.add(24.50);
        freqTable.add(25.96);
        freqTable.add(27.50);
        freqTable.add(29.14);
        freqTable.add(30.87);
        freqTable.add(32.70);
        freqTable.add(34.65);
        freqTable.add(36.71);
        freqTable.add(38.89);
        freqTable.add(41.20);
        freqTable.add(43.65);
        freqTable.add(46.25);
        freqTable.add(49.00);
        freqTable.add(51.91);
        freqTable.add(55.00);
        freqTable.add(58.27);
        freqTable.add(61.74);
        freqTable.add(65.41);
        freqTable.add(69.30);
        freqTable.add(73.42);
        freqTable.add(77.78);
        freqTable.add(82.41);
        freqTable.add(87.31);
        freqTable.add(92.50);
        freqTable.add(98.00);
        freqTable.add(103.83);
        freqTable.add(110.00);
        freqTable.add(116.54);
        freqTable.add(123.47);
        freqTable.add(130.81);
        freqTable.add(138.59);
        freqTable.add(146.83);
        freqTable.add(155.56);
        freqTable.add(164.81);
        freqTable.add(174.61);
        freqTable.add(185.00);
        freqTable.add(196.00);
        freqTable.add(207.65);
        freqTable.add(220.00);
        freqTable.add(233.08);
        freqTable.add(246.94);
        freqTable.add(261.63);
        freqTable.add(277.18);
        freqTable.add(293.66);
        freqTable.add(311.13);
        freqTable.add(329.63);
        freqTable.add(349.23);
        freqTable.add(369.99);
        freqTable.add(392.00);
        freqTable.add(415.30);
        freqTable.add(440.00);
        freqTable.add(466.16);
        freqTable.add(493.88);
        freqTable.add(523.25);
        freqTable.add(554.37);
        freqTable.add(587.33);
        freqTable.add(622.25);
        freqTable.add(659.26);
        freqTable.add(698.46);
        freqTable.add(739.99);
        freqTable.add(783.99);
        freqTable.add(830.61);
        freqTable.add(880.00);
        freqTable.add(932.33);
        freqTable.add(987.77);
        freqTable.add(1046.50);
        freqTable.add(1108.73);
        freqTable.add(1174.66);
        freqTable.add(1244.51);
        freqTable.add(1318.51);
        freqTable.add(1396.91);
        freqTable.add(1479.98);
        freqTable.add(1567.98);
        freqTable.add(1661.22);
        freqTable.add(1760.00);
        freqTable.add(1864.66);
        freqTable.add(1975.53);
        freqTable.add(2093.00);
        freqTable.add(2217.46);
        freqTable.add(2349.32);
        freqTable.add(2489.02);
        freqTable.add(2637.02);
        freqTable.add(2793.83);
        freqTable.add(2959.96);
        freqTable.add(3135.96);
        freqTable.add(3322.44);
        freqTable.add(3520.00);
        freqTable.add(3729.31);
        freqTable.add(3951.07);
        freqTable.add(4186.01);
        freqTable.add(4434.92);
        freqTable.add(4698.64);
        freqTable.add(4978.03);
        freqTable.add(5274.04);
        freqTable.add(5587.65);
        freqTable.add(5919.91);
        freqTable.add(6271.93);
        freqTable.add(6644.88);
        freqTable.add(7040.00);
        freqTable.add(7458.62);
        freqTable.add(7902.13);
        freqTable.add(8372.02);
        freqTable.add(8869.84);
        freqTable.add(9397.27);
        freqTable.add(9956.06);
        freqTable.add(10548.08);
        freqTable.add(11175.30);
        freqTable.add(11839.82);
        freqTable.add(12543.85);
        freqTable.add(13289.75);

        System.out.println("Frequencies table charged.");

        List<Double> frequencies = new ArrayList<Double>();
        for (int i = 0; i < notes.size(); i++) {
            frequencies.add(freqTable.get(notes.get(i)));
        }

        return frequencies;
    }

}
