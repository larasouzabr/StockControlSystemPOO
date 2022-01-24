package productPackage;

public enum AvalProductEnum {
    INDISPONIVEL,   // Sem Estoque.
    MUITOBAIXA,     // Estoque <= 10%
    BAIXA,          // 25% >= Estoque > 10%
    MEDIA,          // 50 >= Estoque > 25%
    ALTA,           // 75% >= Estoque > 50%
    MUITOALTA       // Estoque cheio || > 75%
}


