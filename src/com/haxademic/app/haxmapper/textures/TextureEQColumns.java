package com.haxademic.app.haxmapper.textures;

import com.haxademic.core.app.P;
import com.haxademic.core.math.MathUtil;

public class TextureEQColumns 
extends BaseTexture {

	protected int _numLines = 40;
	protected boolean _hasStroke = true;
	protected boolean _barsGrow = false;

	public TextureEQColumns( int width, int height ) {
		super();

		buildGraphics( width, height );
	}

	public void newLineMode() {
		_numLines = MathUtil.randRange(20, 30);
		_hasStroke = !_hasStroke;
		_barsGrow = MathUtil.randBoolean(P.p);
	}

	public void updateDraw() {
		_texture.clear();
		
		float eqW = _texture.width / _numLines;
		// float spectrumInterval = ( 512f / _numLines );
		float avergeInterval = ( 32f / _numLines );
		

		if( _hasStroke == true ) {
			_texture.stroke(0);
			_texture.strokeWeight(1);
		} else {
			_texture.noStroke();
		}

		if( _barsGrow == true ) {
			for( int i=0; i < _numLines; i++ ) {
				_texture.fill( _colorEase.colorInt() );
				_texture.rect(i * eqW, 0, eqW, P.p.audioIn.getEqAvgBand( P.floor(i*avergeInterval) ) * _texture.height * 0.8f );  //  P.p.audioIn.getEqBand( P.floor(i*spectrumInterval)%512 ) * 50
			}
		} else {
			for( int i=0; i < _numLines; i++ ) {
				_texture.fill( _colorEase.colorInt(), P.constrain( P.p.audioIn.getEqAvgBand( P.floor(i*avergeInterval) ) * 255, 0, 255 ) );
				_texture.rect(i * eqW, 0, eqW, _texture.height );  //  P.p.audioIn.getEqBand( P.floor(i*spectrumInterval)%512 ) * 50
			}
		}
	}
}
