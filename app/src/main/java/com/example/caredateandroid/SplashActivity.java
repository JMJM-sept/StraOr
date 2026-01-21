package com.example.caredateandroid;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SplashActivity extends AppCompatActivity {

    private static final long SHOW_BG_MS = 5000;   // 5 segundos solo fondo
    private static final long LOGO_ANIM_MS = 800;  // animación del logo
    private static final long NEXT_DELAY_MS = 900; // espera antes de Main

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Root layout (para animar el gradiente)
        ConstraintLayout root = findViewById(R.id.splashRoot);

        // Activar animación del fondo degradado
        AnimationDrawable bgAnimation = (AnimationDrawable) root.getBackground();
        if (bgAnimation != null) {
            bgAnimation.setEnterFadeDuration(800);
            bgAnimation.setExitFadeDuration(800);
            bgAnimation.start();
        }

        // Logo
        View logo = findViewById(R.id.splashLogo);

        // Logo inicia oculto y ligeramente más pequeño
        logo.setAlpha(0f);
        logo.setScaleX(0.85f);
        logo.setScaleY(0.85f);

        // A los 5s aparece el logo y luego pasa a MainActivity
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            logo.animate()
                    .alpha(1f)
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(LOGO_ANIM_MS)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                                overridePendingTransition(
                                        android.R.anim.fade_in,
                                        android.R.anim.fade_out
                                );
                            }, NEXT_DELAY_MS);
                        }
                    })
                    .start();
        }, SHOW_BG_MS);
    }
}
