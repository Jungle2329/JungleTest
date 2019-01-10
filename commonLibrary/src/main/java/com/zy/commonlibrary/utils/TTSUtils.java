package com.zy.commonlibrary.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;

import com.zy.commonlibrary.R;
import com.zy.commonlibrary.base.LogUtils;
import com.zy.commonlibrary.base.MyApplication;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Jungle on 2018/11/12 0012.
 * 一些手机支持原生播报
 * <p>
 * 播放语音，多次执行会按队列播放，单例模式
 */

public class TTSUtils {

    private TextToSpeech mTextToSpeech;
    private boolean soundPoolEnable = true;

    private TTSUtils() {

    }

    private static class InstanceInner {
        private static TTSUtils instance = new TTSUtils();
    }

    public static TTSUtils getInstance() {
        return InstanceInner.instance;
    }

    public void play(final String text) {
        mTextToSpeech = new TextToSpeech(MyApplication.getAppContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    playData(text);
                } else {
                    playWithSoundPool(text);
                    mTextToSpeech.shutdown();
                }
            }
        });
    }

    private void playData(String text) {
        int ttsState = mTextToSpeech.setLanguage(Locale.CHINESE);
        if (ttsState == TextToSpeech.SUCCESS) {
            mTextToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                @Override
                public void onStart(String utteranceId) {
                }

                @Override
                public void onDone(String utteranceId) {
                    //资源释放
                    mTextToSpeech.shutdown();
                }

                @Override
                public void onError(String utteranceId) {
                    //资源释放
                    mTextToSpeech.shutdown();
                }
            });
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mTextToSpeech.speak(text, TextToSpeech.QUEUE_ADD, null, "AppPay");
            } else {
                mTextToSpeech.speak(text, TextToSpeech.QUEUE_ADD, null);
            }
        } else {
            playWithSoundPool(text);
            if (mTextToSpeech != null) {
                mTextToSpeech.stop();
                mTextToSpeech.shutdown();
            }
        }
    }

    public void playWithSoundPool(final String soundTexts) {
        soundPoolEnable = true;
        final SoundPool mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        //小猪惠丰到账
        final int getmoney = mSoundPool.load(MyApplication.getAppContext(), R.raw.get_money, 1);
        //0~9
        final int zero = mSoundPool.load(MyApplication.getAppContext(), R.raw.zero, 1);
        final int one = mSoundPool.load(MyApplication.getAppContext(), R.raw.one, 1);
        final int two = mSoundPool.load(MyApplication.getAppContext(), R.raw.two, 1);
        final int three = mSoundPool.load(MyApplication.getAppContext(), R.raw.three, 1);
        final int four = mSoundPool.load(MyApplication.getAppContext(), R.raw.four, 1);
        final int five = mSoundPool.load(MyApplication.getAppContext(), R.raw.five, 1);
        final int six = mSoundPool.load(MyApplication.getAppContext(), R.raw.six, 1);
        final int seven = mSoundPool.load(MyApplication.getAppContext(), R.raw.seven, 1);
        final int eight = mSoundPool.load(MyApplication.getAppContext(), R.raw.eight, 1);
        final int nine = mSoundPool.load(MyApplication.getAppContext(), R.raw.nine, 1);
        //十百千万
        final int shi = mSoundPool.load(MyApplication.getAppContext(), R.raw.shi, 1);
        final int bai = mSoundPool.load(MyApplication.getAppContext(), R.raw.bai, 1);
        final int qian = mSoundPool.load(MyApplication.getAppContext(), R.raw.qian, 1);
        final int wan = mSoundPool.load(MyApplication.getAppContext(), R.raw.wan, 1);
        //元角分
        final int yuan = mSoundPool.load(MyApplication.getAppContext(), R.raw.yuan, 1);
        final int jiao = mSoundPool.load(MyApplication.getAppContext(), R.raw.jiao, 1);
        final int fen = mSoundPool.load(MyApplication.getAppContext(), R.raw.fen, 1);
        //点
        final int dot = mSoundPool.load(MyApplication.getAppContext(), R.raw.dot, 1);

        AudioManager mgr = (AudioManager) MyApplication.getAppContext().getSystemService(Context.AUDIO_SERVICE);
        //最大音量
        float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        //当前音量
        float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
        final float volume = streamVolumeCurrent / streamVolumeMax;

        mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(final SoundPool soundPool, int sampleId, int status) {
                if (soundPoolEnable) {
                    soundPoolEnable = false;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            mSoundPool.play(getmoney, volume, volume, 1, 0, 1.0f);
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                            String[] strs = soundTexts.split("");
                            for (String str : strs) {
                                switch (str) {
                                    case "一":
                                        soundPoolPlay(mSoundPool, one, volume, volume);
                                        break;
                                    case "二":
                                        soundPoolPlay(mSoundPool, two, volume, volume);
                                        break;
                                    case "三":
                                        soundPoolPlay(mSoundPool, three, volume, volume);
                                        break;
                                    case "四":
                                        soundPoolPlay(mSoundPool, four, volume, volume);
                                        break;
                                    case "五":
                                        soundPoolPlay(mSoundPool, five, volume, volume);
                                        break;
                                    case "六":
                                        soundPoolPlay(mSoundPool, six, volume, volume);
                                        break;
                                    case "七":
                                        soundPoolPlay(mSoundPool, seven, volume, volume);
                                        break;
                                    case "八":
                                        soundPoolPlay(mSoundPool, eight, volume, volume);
                                        break;
                                    case "九":
                                        soundPoolPlay(mSoundPool, nine, volume, volume);
                                        break;
                                    case "零":
                                        soundPoolPlay(mSoundPool, zero, volume, volume);
                                        break;
                                    case "拾":
                                        soundPoolPlay(mSoundPool, shi, volume, volume);
                                        break;
                                    case "佰":
                                        soundPoolPlay(mSoundPool, bai, volume, volume);
                                        break;
                                    case "仟":
                                        soundPoolPlay(mSoundPool, qian, volume, volume);
                                        break;
                                    case "万":
                                        soundPoolPlay(mSoundPool, wan, volume, volume);
                                        break;
                                    case "元":
                                        soundPoolPlay(mSoundPool, yuan, volume, volume);
                                        break;
                                    case "角":
                                        soundPoolPlay(mSoundPool, jiao, volume, volume);
                                        break;
                                    case "分":
                                        soundPoolPlay(mSoundPool, fen, volume, volume);
                                        break;
                                    case "点":
                                        soundPoolPlay(mSoundPool, dot, volume, volume);
                                        break;

                                }
                            }
                            mSoundPool.release();
                        }
                    }).start();
                }
            }
        });

    }

    private void soundPoolPlay(SoundPool mSoundPool, int soundId, float leftVolume, float rightVolume) {
        mSoundPool.play(soundId, leftVolume, rightVolume, 1, 0, 1.0f);
        try {
            Thread.sleep(340);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }


    /**
     * 根据文字生成音频文件
     *
     * @param text
     */
    public void saveFile(String text) {
        int ttsState = mTextToSpeech.setLanguage(Locale.CHINESE);
        LogUtils.i("zhangyi", "ttsState:" + ttsState);
        File file = new File(Environment.getExternalStorageDirectory() + "/smallpig/tempSounds");
        if (!file.exists()) {
            file.mkdirs();
        }
        File filePath = new File(file, text + ".wav");
        mTextToSpeech.synthesizeToFile(text, new HashMap<String, String>(), filePath.toString());
    }
}
