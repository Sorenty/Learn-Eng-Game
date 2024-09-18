package com.akstae.studingenglishapp

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.view.View
import android.widget.Button

class SoundButtonHelper(private val context: Context) {
    private val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.sounduse)
    private val preferences: SharedPreferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)

    fun setSoundOnClickListener(button: Button, originalClickListener: View.OnClickListener?) {
        button.setOnClickListener { view ->
            if (isSoundEnabled()) {
                playClickSound()
            }
            originalClickListener?.onClick(view)
        }
    }

    private fun playClickSound() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.prepare()
        }
        mediaPlayer.start()
    }

    private fun isSoundEnabled(): Boolean {
        return preferences.getBoolean("sound_enabled", true)
    }

    fun release() {
        mediaPlayer.release()
    }
}
