package odev;


public class Ogrenci {

	public String OgrenciId;
	public String Adi;
	public String Memleketi;

	public Ogrenci() {
		// TODO Auto-generated constructor stub
	}
	public String getAdi() {
		return Adi;
	}
	public String getMemleketi() {
		return Memleketi;
	}
	public String getOgrenciId() {
		return OgrenciId;
	}
	
	public void setAdi(String adi) {
		Adi = adi;
	}
	public void setMemleketi(String memleketi) {
		Memleketi = memleketi;
	}
	public void setOgrenciId(String ogrenciId) {
		OgrenciId = ogrenciId;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.OgrenciId+" "+this.Adi+" "+this.Memleketi;
	}
}
