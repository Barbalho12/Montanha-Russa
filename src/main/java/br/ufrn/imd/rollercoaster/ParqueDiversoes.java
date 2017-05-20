package br.ufrn.imd.rollercoaster;

public class ParqueDiversoes extends Thread{
	private MontanhaRussa montanhaRussa;
	
	public static int GLOBAL_TIME;
	
	public ParqueDiversoes(){
		GLOBAL_TIME = 0;
		this.start();
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GLOBAL_TIME = GLOBAL_TIME + 1;
		}
	}
	
	public ParqueDiversoes(MontanhaRussa montanhaRussa){
		this.montanhaRussa = montanhaRussa;
		GLOBAL_TIME = 0;
		this.start();
	}

	public MontanhaRussa getMontanhaRussa() {
		return montanhaRussa;
	}

	public void setMontanhaRussa(MontanhaRussa montanhaRussa) {
		this.montanhaRussa = montanhaRussa;
	}

}
