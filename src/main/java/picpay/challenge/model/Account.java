package picpay.challenge.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user; 
	
	@Column(name = "number_account", nullable = false )
	private String numberAccount;
	
	@Column(name = "balance", nullable = true)
	private Double balance = 0.0; 
	
	@JsonIgnore
	@OneToMany(mappedBy = "sender")
	private List<Transaction> sentTransactions;
	
	@JsonIgnore
	@OneToMany(mappedBy = "receiver")
	private List<Transaction> receivedTransactions;
}
