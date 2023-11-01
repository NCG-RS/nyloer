package com.nyloer.nylostats;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@EqualsAndHashCode
public
class Stats
{
	public String totalTime;
	public String bossTime;
	public String wavesTime;

	public int stallCountPre;
	public int stallCount21;
	public int stallCount22to27;
	public int stallCount28;
	public int stallCount29;
	public int stallCount30;

	public int bigsAlive22;
	public int bigsAlive29;
	public int bigsAlive30;
	public int bigsAlive31;

	Stats(
		String totalTime,
		String bossTime,
		String wavesTime,
		int stallCountPre,
		int stallCount21,
		int stallCount22to27,
		int stallCount28,
		int stallCount29,
		int stallCount30,
		int bigsAlive22,
		int bigsAlive29,
		int bigsAlive30,
		int bigsAlive31
		)
	{
		this.totalTime = totalTime;
		this.bossTime = bossTime;
		this.wavesTime = wavesTime;

		this.stallCountPre = stallCountPre;
		this.stallCount21 = stallCount21;
		this.stallCount22to27 = stallCount22to27;
		this.stallCount28 = stallCount28;
		this.stallCount29 = stallCount29;
		this.stallCount30 = stallCount30;

		this.bigsAlive22 = bigsAlive22;
		this.bigsAlive29 = bigsAlive29;
		this.bigsAlive30 = bigsAlive30;
		this.bigsAlive31 = bigsAlive31;
	}
}