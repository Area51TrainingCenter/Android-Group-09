package com.area51.videoplayer;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.area51.utils.Utilitarios;

public class MainActivity extends Activity 
implements Callback, OnPreparedListener, 
OnBufferingUpdateListener, OnCompletionListener, 
OnErrorListener, OnInfoListener, OnVideoSizeChangedListener,
OnSeekBarChangeListener 
 {

	String TAG = "VideoPlayer";
	
	//VARIABLES DE REPRODUCCIÓN
	SurfaceView surface;
	SurfaceHolder holder;
	MediaPlayer mp;
	Handler handler;
	int delay = 1000;
	
	//VARIABLES DE DISEÑO
	TextView txtnombre;
	TextView txttiempo;
	LinearLayout barcontrol;
	ImageView btnplay;
	ImageView btnpause;
	SeekBar seekbar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		surface = (SurfaceView)findViewById(R.id.surface);
		txtnombre = (TextView)findViewById(R.id.txtnombre);
		txttiempo = (TextView)findViewById(R.id.txttiempo);
		barcontrol = (LinearLayout)findViewById(R.id.barcontrol);
		btnplay = (ImageView)findViewById(R.id.btnplay);
		btnpause = (ImageView)findViewById(R.id.btnpause);
		seekbar = (SeekBar)findViewById(R.id.seekbar);
		
		//INICIALIZAMOS EL HANDLER
		handler = new Handler();
		
	}
	
	@Override
	protected void onResume() {

		super.onResume();
		//INICIALIZAMOS EL HOLDER
		holder = surface.getHolder();
		holder.addCallback(this);
		
		//INICIALIZAMOS EL MEDIA PLAYER
		mp = new MediaPlayer();
		//CONFIGURAMOS LOS EVENTOS DEL MEDIA PLAYER
		mp.setOnPreparedListener(this);
		mp.setOnBufferingUpdateListener(this);
		mp.setOnCompletionListener(this);
		mp.setOnErrorListener(this);
		mp.setOnInfoListener(this);
		mp.setOnVideoSizeChangedListener(this);
		
		//Para la calidad del audio
		mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
		
		//CARGAMOS EL VIDEO		
		try {
			//PARA REPRODUCCIÓN INTERNA		
			mp.setDataSource( 
					this,					
					Uri.parse( Utilitarios.rutavideo )  );
			
			//PARA STREAMING
			//mp.setDataSource( Utilitarios.rutaStreaming);
			
		} catch (IOException e) {
			//Mensaje de alerta
			e.printStackTrace();
		}
		
		//SETEAMOS EVENTOS CLIC
		btnplay.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				
				if( !mp.isPlaying() ){
					mp.start();
					btnplay.setVisibility(View.GONE);
					btnpause.setVisibility(View.VISIBLE);
					mp.seekTo(Utilitarios.tiempo);
					handler.postDelayed(hilo, delay);
				}
				
			}
		});
		
		btnpause.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//PREGUNTAMOS EL ESTADO DE REPRODUCCIÓN
				if( mp.isPlaying() ){					
					mp.pause();					
					btnpause.setVisibility(View.GONE);
					btnplay.setVisibility(View.VISIBLE);
					
					Utilitarios.tiempo = mp.getCurrentPosition();
					
					//QUITAMOS EL HILO DEL HANDLER
					handler.removeCallbacks(hilo);
					
				}
				
			}
		});
		
	}
	
	@Override
	protected void onPause() {
		
		super.onPause();
		
		if(mp.isPlaying()){
			mp.pause();
			Utilitarios.tiempo = mp.getCurrentPosition();
			handler.removeCallbacks(hilo);
		}
		
	}
	
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		
		handler.removeCallbacks(hilo);
	}
	
	
	//Para mostrar el timepo
	public Runnable hilo = new Runnable() {
		
		@Override
		public void run() {
			if( mp.isPlaying() ){
				
				//Obtenemos el tiempo de reproducción actual
				Utilitarios.tiempo = mp.getCurrentPosition();
				//Lo asignamos al SeekBar
				seekbar.setProgress( Utilitarios.tiempo );				
				//Lo mostramos en el textview
				contador();
				handler.postDelayed(hilo, delay);
			}
			
		}
	};
	
	
	public void contador(){
		//00:03:40 // 02:30:50
		txttiempo.setText(  
				textoTiempo(Utilitarios.tiempo) + " / " +
						textoTiempo(mp.getDuration()) 
				);
	}


	public String textoTiempo( int time ){
		String tiempo = 
				String.format( "%2d : %2d : %2d",
						TimeUnit.MILLISECONDS.toHours(time),
						TimeUnit.MILLISECONDS.toMinutes(time),
						TimeUnit.MILLISECONDS.toSeconds(time)						
						);
				
		return tiempo;
	}
	
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		
		mp.setDisplay(holder);
		//CARGAMOS LA INFORMACIÓN DEL VIDEO 
		//EN EL MEDIA PLAYER
		//mp.prepareAsync(); //Para reproducción streaming
		
		try {
			
			mp.prepare();
			
		} catch (IllegalStateException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		
		
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		
		//VERIFICAMOS EL TIEMPO DE REPRODUCCIÓN
		if( Utilitarios.tiempo != 0 ){
			mp.seekTo(Utilitarios.tiempo);
		}
		
		//REPRODUCIMOS EL VIDEO
		mp.start();
		handler.postDelayed(hilo, delay);
		
	}

	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent) {
		
		
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		//TERMINO LA REPRODUCCIÓN DEL VIDEO 
		handler.removeCallbacks(hilo);
		
		btnpause.setVisibility(View.GONE);
		btnplay.setVisibility(View.VISIBLE);
		Utilitarios.tiempo = 0;
		
		
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		
		return false;
	}

	@Override
	public boolean onInfo(MediaPlayer mp, int what, int extra) {
		
		return false;
	}

	@Override
	public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
		
		seekbar.setMax(mp.getDuration());
		seekbar.setProgress( Utilitarios.tiempo );
		seekbar.setOnSeekBarChangeListener(this);
		
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		
		if( fromUser ){
			Utilitarios.tiempo = seekbar.getProgress();
			contador();
		}
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		//Empieza el arrastre del usuario
		//y desactivamos el hilo
		handler.removeCallbacks(hilo);
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		//Se detiene el arrastre
		handler.postDelayed(hilo, delay);
		mp.seekTo(Utilitarios.tiempo);
	}

	
	


}
