package br.com.interfile.limparInstancias.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.interfile.limparInstacias.model.Task;
import br.com.interfile.limparInstancias.utils.IgnoreCertificateUtil;


@Service
public class TaskService {

	public List<Task> obterTarefas(String dominioUrl, String activityId, String tenantId) {
		System.out.println("Entrou no service");
		RestTemplate rt = IgnoreCertificateUtil.getRestTemplate();
		String requestUrl = dominioUrl + "/engine-rest/task?taskDefinitionKey="+ activityId;
		System.out.println(requestUrl);
		ResponseEntity<List<Task>> response = null;
		try {
			response = rt.exchange(
					requestUrl,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<List<Task>>(){});
		} catch (Exception e) {
			System.out.println("[STATUS] Não existem tarefas com instâncias abertas.");
			return null;
		}
		List<Task> allTasks = response.getBody();
		List<Task> tasks = new ArrayList<>();
		for (Task task : allTasks) {
			if(task.getTenantId().equals(tenantId)) {
				tasks.add(task);
			}
		}
		return tasks;
	}
}
