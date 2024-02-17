package com.example.todoappBE.payloads;

import org.hibernate.validator.constraints.Email;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestBody {
	@NotNull(message = "Il campo 'Nome' è vuoto")
	private String nome;
	@NotNull(message = "Il campo 'Cognome' è vuoto")
	private String cognome;
//	@SuppressWarnings("deprecation")
	@NotNull(message = "Il campo 'E-mail' è vuoto")
	@Email(message = "L'email inserita è in un formato non valido")
	private String email;
	@NotNull(message = "Il campo 'Password' è vuoto")
	@Pattern(regexp = ".*[a-z].*", message = "La password deve contenere almeno una lettera minuscola")
	@Pattern(regexp = ".*[A-Z].*", message = "La password deve contenere almeno una lettera maiuscola")
	@Pattern(regexp = ".*\\d.*", message = "La password deve contenere almeno un numero")
	@Size(min = 8, max = 30, message = "La password deve avere minimo 8 caratteri, massimo 30")
	private String password;

	public UserRequestBody(String nome, String cognome, String email, String password) {

		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;

	}

	@Override
	public String toString() {
		return "UserRequestBody [nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", password=" + password
				+ "]";
	}
}
