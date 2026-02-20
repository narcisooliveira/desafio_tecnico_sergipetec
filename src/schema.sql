CREATE DATABASE desafio_pedidos;
USE desafio_pedidos;

-- ========================
-- CLIENTE
-- ========================
CREATE TABLE cliente (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(150) NOT NULL,
                         email VARCHAR(150) NOT NULL UNIQUE,
                         data_cadastro DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx_cliente_nome ON cliente(nome);

-- ========================
-- PRODUTO
-- ========================
CREATE TABLE produto (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         descricao VARCHAR(200) NOT NULL,
                         valor DECIMAL(10,2) NOT NULL,
                         quantidade_estoque INT NOT NULL,
                         data_cadastro DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx_produto_descricao ON produto(descricao);

-- ========================
-- PEDIDO
-- ========================
CREATE TABLE pedido (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        cliente_id BIGINT NOT NULL,
                        data_pedido DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        CONSTRAINT fk_pedido_cliente
                            FOREIGN KEY (cliente_id)
                                REFERENCES cliente(id)
                                ON DELETE RESTRICT
                                ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx_pedido_cliente ON pedido(cliente_id);
CREATE INDEX idx_pedido_data ON pedido(data_pedido);

-- ========================
-- ITEM_PEDIDO
-- ========================
CREATE TABLE item_pedido (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             pedido_id BIGINT NOT NULL,
                             produto_id BIGINT NOT NULL,
                             valor DECIMAL(10,2) NOT NULL,
                             quantidade INT NOT NULL,
                             desconto DECIMAL(10,2) DEFAULT 0.00,
                             CONSTRAINT fk_item_pedido
                                 FOREIGN KEY (pedido_id)
                                     REFERENCES pedido(id)
                                     ON DELETE CASCADE,
                             CONSTRAINT fk_item_produto
                                 FOREIGN KEY (produto_id)
                                     REFERENCES produto(id)
                                     ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx_item_pedido_pedido ON item_pedido(pedido_id);
CREATE INDEX idx_item_pedido_produto ON item_pedido(produto_id);
