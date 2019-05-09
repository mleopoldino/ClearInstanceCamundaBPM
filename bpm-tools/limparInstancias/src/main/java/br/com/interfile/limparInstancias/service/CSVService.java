package br.com.interfile.limparInstancias.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.interfile.limparInstacias.model.ByCsv;

@Service
public class CSVService {

	public List<ByCsv> processInstances(InputStream is) {
		BufferedReader br = null;
		String linha = "";
		List<ByCsv> dados = new ArrayList<>();
		try {
			boolean firstLine = true;
			br = new BufferedReader(new InputStreamReader(is));
			while ((linha = br.readLine()) != null) {
				if(firstLine) {
					firstLine = false;
				} else {
					ByCsv byCsv = new ByCsv(linha);
					dados.add(byCsv);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return dados;
	}


}