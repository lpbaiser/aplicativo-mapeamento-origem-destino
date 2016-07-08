package com.example.raphael.projeto_tcc.linhas;

import com.example.raphael.projeto_tcc.bancoDados.OringemDestino;

import java.io.Serializable;

public class LinhaLatLon extends OringemDestino implements Serializable {

private static final long serialVersion = 1L;
	private String lat;
	private String lon;

public String getLat()
{
	return lat;
}

	public void setLat(String lat)
	{
		this.lat = lat;
	}

	public String getLon()
	{
		return lon;
	}

	public void setLon(String lon)
	{
		this.lon = lon;
	}

	@Override
	public String toString()
	{
		return lon;
	}

}
