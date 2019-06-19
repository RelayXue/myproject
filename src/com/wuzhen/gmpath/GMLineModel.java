package com.wuzhen.gmpath;

import com.gh.entity.GmPathLine;


public class GMLineModel extends GmPathLine {
	public void ReadFrom(GmPathLine line) {
		this.setFuid(line.getFuid());
		this.setFno(line.getFno());
		this.setFname(line.getFname());
		this.setFlength(line.getFlength());
		this.setFendY(line.getFendY());
		this.setFendX(line.getFendX());
		this.setFcreateTime(line.getFcreateTime());
		this.setFbeginX(line.getFbeginX());
		this.setFbeginY(line.getFbeginY());
		this.setForderByAsc(line.getForderByAsc());
		this.setForderByDesc(line.getForderByDesc());
		String[] tp = this.getForderByAsc().replace(",", " ").trim().split(" ");
		if (tp.length == 2) {
			try {
				this.FBeginNo = Integer.valueOf(tp[0]);
				this.FEndNo = Integer.valueOf(tp[1]);
			} catch (Exception ex) {
			}
		}
		if (line.getFstring() != null) {
			this.PointS = GMPointCollection
					.FromStringByComma(line.getFstring());
		}
	}

	public int FBeginNo = 0;
	public int FEndNo = 0;
	public GMPointCollection PointS = new GMPointCollection();

	public GMLineModel Clone(){
		GMLineModel m = new GMLineModel();
		m.ReadFrom(this);
		m.FBeginNo = this.FBeginNo;
		m.FEndNo = this.FEndNo;
		for(int u=0;u<this.PointS.size();u++){
			m.PointS.add(this.PointS.get(u));
		}
		return m;
	}}
