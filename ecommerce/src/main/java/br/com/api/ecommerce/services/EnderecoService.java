package br.com.api.ecommerce.services;


import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.api.ecommerce.dto.ViaCepDTO;
import br.com.api.ecommerce.entities.Endereco;
import br.com.api.ecommerce.repositories.EnderecoRepository;

@Service
public class EnderecoService {


	@Autowired 
	EnderecoRepository enderecoRepo;
	
	private static final String VIA_CEP_URL = "https://viacep.com.br/ws/";
	
	public List<Endereco> listarEnderecos() {
		return enderecoRepo.findAll();
	}

	public Endereco buscarEnderecoPorId(Integer id) {
		return enderecoRepo.findById(id)
		        .orElseThrow(() -> new NoSuchElementException("Endereço" + id)); 
	}

	public Endereco salvarEndereco(Endereco enderecoCep) {
		Endereco endereco = consultaCep(enderecoCep);
		return enderecoRepo.save(endereco);
	}
		
	public Endereco consultaCep(Endereco endereco) {
		RestTemplate restTemplate = new RestTemplate();
		String viaCepUrl = VIA_CEP_URL + endereco.getCep()+ "/json";
		ViaCepDTO cepDto = restTemplate.getForObject
				(viaCepUrl, ViaCepDTO.class);
		try {
		endereco.setCep(cepDto.getCep());
		endereco.setRua(cepDto.getLogradouro());
		endereco.setBairro(cepDto.getBairro());
		endereco.setCidade(cepDto.getLocalidade());
		endereco.setNumero(cepDto.getNumero());
		endereco.setComplemento(cepDto.getComplemento());
		endereco.setUf(cepDto.getUf());
		} catch(NullPointerException e) {
			System.out.println("Insira um cep valido "+ e.getMessage());}
		return endereco;
	}
		

	public Endereco atualizarEndereco (Endereco endereco) {
		return enderecoRepo.save(endereco);
	}

	public Boolean deletarEndereco(Endereco endereco) {
		if(endereco == null)
			return false;
		
		Endereco enderecoExistente = buscarEnderecoPorId(endereco.getIdEndereco());
		
		if(enderecoExistente == null)
			return false;
		
		enderecoRepo.delete(endereco);
		
		Endereco enderecoContinuaExistindo = 
				buscarEnderecoPorId(endereco.getIdEndereco());
		
		if(enderecoContinuaExistindo == null)
			return true;

		return false;	
	}

}



