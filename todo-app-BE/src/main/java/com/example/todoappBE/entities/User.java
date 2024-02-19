package com.example.todoappBE.entities;

import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "utenti")
@NoArgsConstructor
@JsonIgnoreProperties({
		// "password",
		"accountNonExpired", "authorities", "credentialsNonExpired", "accountNonLocked", "username", "enabled" })
public class User implements UserDetails {
	@Id
	@GeneratedValue
	private UUID id;
	private String name;
	private String last_name;
	@Column(nullable = false, unique = true)
	private String email;
	private String password;

	public User(String name, String last_name, String email, String password) {
		this.name = name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getLast_name() {
		return last_name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}


	public void setName(String name) {
		this.name = name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", last_name=" + last_name + ", email=" + email + ", password="
				+ password + "]";
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}



	@Override
	public String getUsername() {
		// Ritorna l'email dell'utente, che verrà utilizzata come username per
		// l'autenticazione
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// Verifica se l'account dell'utente non è scaduto
		// Implementa la logica necessaria, ad esempio controllando la data di scadenza
		// dell'account
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// Verifica se l'account dell'utente non è bloccato
		// Implementa la logica necessaria per controllare lo stato di blocco
		// dell'account
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// Verifica se le credenziali dell'utente non sono scadute
		// Implementa la logica necessaria, ad esempio controllando la data di scadenza
		// delle credenziali
		return true;
	}

	@Override
	public boolean isEnabled() {
		// Verifica se l'utente è abilitato o disabilitato
		// Implementa la logica necessaria per controllare lo stato di abilitazione
		// dell'utente
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

}
