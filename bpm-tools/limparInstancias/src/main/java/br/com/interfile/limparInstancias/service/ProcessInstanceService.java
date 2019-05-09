package br.com.interfile.limparInstancias.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.interfile.limparInstacias.model.ByCsv;
import br.com.interfile.limparInstacias.model.Task;
import br.com.interfile.limparInstancias.utils.IgnoreCertificateUtil;

@Service
public class ProcessInstanceService {

	public void excluirInstancias(List<Task> tasks, String dominioUrl) {
		int contador = 0;
		for (Task task : tasks) {
				String deleteUrl = dominioUrl + "/engine-rest/process-instance/" + task.getProcessInstanceId();
				RestTemplate rt = IgnoreCertificateUtil.getRestTemplate();
				rt.delete(deleteUrl);
				contador++;
		}
		System.out.println("[STATUS] Foram excluídas " + contador + " instâncias");
	}

	public void excluirInstanciasCSV(List<ByCsv> processInstances, String dominioUrl) {
		int contador = 0;
		for (ByCsv processInstance : processInstances) {
			String deleteUrl = dominioUrl + "/engine-rest/process-instance/" + processInstance.getProcId();
			RestTemplate rt = IgnoreCertificateUtil.getRestTemplate();
			try {
				rt.delete(deleteUrl); 

				contador++;
			} catch (Exception e) {
				System.out.println("[STATUS] A instancia " + processInstance + " nao existe.");
			}
		}
		System.out.println("[STATUS] Foram excluídas " + contador + " instâncias");
	}

}