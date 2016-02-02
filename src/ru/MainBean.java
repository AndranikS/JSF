package ru;

import java.io.Serializable;
import java.math.BigDecimal;
import com.xj.anylogic.engine.Engine;
import infl_2015.Main;
import infl_2015.CustomExperiment;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
@ManagedBean(name="model") // аннотация указывающая что это managed bean и поэтому мы его не описываем в faces-config.xml
@SessionScoped // аннотация указывающая что это managed bean является сессионным
public class MainBean {

	private Exogen[] exogenList;
	private Endogen[] endogenList;


	public Exogen[] getExogenList() {
		return exogenList;
	}
	public Endogen[] getEndogenList() {
		return endogenList;
	}



	public void calc() { // Прогон модели AnyLogic
		String[] date_v;
		double[] dollar_v;
		double[] gas_v;
		double[] oil_v;

		dollar_v = new double[8];
		gas_v = new double[8];
		oil_v = new double[8];

		double[] ugas_v;
		double[] pi_v;
		double[] pippi_v;

		date_v = new String[8];
		ugas_v = new double[8];
		pi_v = new double[8];
		pippi_v = new double[8];

		CustomExperiment s = new infl_2015.CustomExperiment(null);
		Engine d = s.createEngine();
		Main m = new Main(d, null, null);
		m.setParametersToDefaultValues();
		d.start(m);

		//Запуск модели на выбранном диапазоне расчета
		double[] index;

		index = new double[8];

		for(int i = 0; i <8 ; i++) {
			index[i] = (double) (i + 1);
			dollar_v[i] = exogenList[i].getDollar();
			gas_v[i] = exogenList[i].getGas();
			oil_v[i] = exogenList[i].getOil();

		}

		m.Uga.setArgumentsAndValues(index,gas_v);
		m.dollar.setArgumentsAndValues(index, dollar_v);
		m.wo.setArgumentsAndValues(index, oil_v);

		int counter=0;

		while(d.time()<8.0)
		{
			date_v[counter] = Integer.toString(counter+1);
			ugas_v[counter]= m.Ugas;
			pi_v[counter] = m.pi;
			pippi_v[counter]=m.pippi;

			d.step(); //Запускаем новый поток (пошаговый прогон модели
			counter=(int)d.time();
			// System.out.println(m.Ugas);
		}


		endogenList = new Endogen[8];
		for (int j = 0; j < 8; j++ )
			endogenList[j] = new Endogen(date_v[j], ugas_v[j], pi_v[j], pippi_v[j]);



	}


	public MainBean(){ // Конструктор основного класса
		String[] date_v;
		double[] dollar_v;
		double[] gas_v;
		double[] oil_v;
		//Инициализация значений переменных
		date_v = new String[8];
		dollar_v = new double[8];
		gas_v = new double[8];
		oil_v = new double[8];

		CustomExperiment s = new infl_2015.CustomExperiment(null);
		Engine d = s.createEngine();
		Main m = new Main(d, null, null);
		m.setParametersToDefaultValues();
		d.start(m);

		//Запуск модели на выбранном диапазоне расчета
		double[] index;
		double[] m1;
		index = new double[8];
		m1 = new double[8];
		for(int i = 0; i <8 ; i++)
			index[i] = (double)(i+1);

		//m.Uga.setArgumentsAndValues(index, m1);
		int counter=0;
		while(d.time()<8.0)
		{
			date_v[counter] = Integer.toString(counter+1);
			dollar_v[counter]= m.dollar.get(counter+1);
			gas_v[counter] = m.Uga.get(counter+1);
			oil_v[counter] = m.wo.get(counter+1);

			d.step(); //Запускаем новый поток (пошаговый прогон модели
			counter=(int)d.time();
			// System.out.println(m.Ugas);
		}


		exogenList = new Exogen[8];
		for (int j = 0; j < 8; j++ )
			exogenList[j] = new Exogen(date_v[j], dollar_v[j], gas_v[j], oil_v[j]);

		endogenList = new Endogen[8];
		for (int j = 0; j < 8; j++ )
			endogenList[j] = new Endogen(date_v[j], 0.0, 0.0, 0.0);
	}

}