package br.study.solid.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long order_id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user_id;

    @Column(unique = true)
    @NotEmpty
    private String produto;
    private String descricao;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date ordered_date;

    @Positive
    private Long price;

    public Orders() {
    }

    public Orders(Users user_id, String produto, String descricao, Date ordered_date, long price) {
        this.user_id = user_id;
        this.produto = produto;
        this.descricao = descricao;
        this.ordered_date = ordered_date;
        this.price = price;
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public Users getUser_id() {
        return user_id;
    }

    public void setUser_id(Users user_id) {
        this.user_id = user_id;
    }

    public @NotEmpty String getProduto() {
        return produto;
    }

    public void setProduto(@NotEmpty String produto) {
        this.produto = produto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getOrdered_date() {
        return ordered_date;
    }

    public void setOrdered_date(Date ordered_date) {
        this.ordered_date = ordered_date;
    }

    @Positive
    public long getPrice() {
        return price;
    }

    public void setPrice(@Positive long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "order_id=" + order_id +
                ", user_id=" + user_id +
                ", produto='" + produto + '\'' +
                ", descricao='" + descricao + '\'' +
                ", ordered_date=" + ordered_date +
                ", price=" + price.toString() +
                '}';
    }
}
