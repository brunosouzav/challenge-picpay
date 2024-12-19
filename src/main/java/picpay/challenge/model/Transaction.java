package picpay.challenge.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import picpay.challenge.enus.Status;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	
	@ManyToOne
	@JoinColumn(name = "sender_id", nullable = false)
	private Account sender;
	
	@ManyToOne
	@JoinColumn(name = "receiver_id", nullable = false)
	private Account receiver;
	
	@Column(name = "value", nullable = false)
	private Double value;

	private LocalDateTime data;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	private Status status = Status.PENDING;

	public Transaction(Account sender, Account receiver, Double value, LocalDateTime data, Status status) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.value = value;
		this.data = data;
		this.status = status;
	}
	
	
	
	
	
}
