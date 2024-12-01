-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 01/12/2024 às 22:23
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `bultzpc`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `categorias`
--

CREATE TABLE `categorias` (
  `id` int(11) NOT NULL,
  `nome` varchar(72) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `categorias`
--

INSERT INTO `categorias` (`id`, `nome`) VALUES
(1, 'Placa-Mãe'),
(2, 'Processador'),
(3, 'Placa de Vídeo'),
(4, 'Armazenamento'),
(5, 'Memória RAM'),
(6, 'Fontes de Alimentação'),
(7, 'Gabinetes'),
(8, 'Teclado'),
(9, 'Mouse'),
(10, 'Monitor');

-- --------------------------------------------------------

--
-- Estrutura para tabela `cliente`
--

CREATE TABLE `cliente` (
  `cpf` varchar(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `datanasc` date NOT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `cliente`
--

INSERT INTO `cliente` (`cpf`, `nome`, `datanasc`, `endereco`, `email`) VALUES
('11111111111', 'Teste', '2004-08-01', 'Teste', 'teste@email.com'),
('12345678900', 'Leonardo', '2004-08-10', 'Rua de Papel, 123', 'leo@email.com');

-- --------------------------------------------------------

--
-- Estrutura para tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `cpf` varchar(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `datanasc` date NOT NULL,
  `cargo` varchar(50) NOT NULL,
  `salario` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `itenspedido`
--

CREATE TABLE `itenspedido` (
  `id` int(11) NOT NULL,
  `pedidoId` int(11) DEFAULT NULL,
  `produtoId` int(11) DEFAULT NULL,
  `quantidade` int(11) NOT NULL,
  `preco` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `itenspedido`
--

INSERT INTO `itenspedido` (`id`, `pedidoId`, `produtoId`, `quantidade`, `preco`) VALUES
(5, 5, 1, 1, 379.81),
(6, 5, 5, 1, 3124.60),
(7, 6, 10, 1, 3803.49),
(8, 6, 1, 5, 379.81),
(9, 6, 8, 3, 1230.66),
(10, 7, 4, 1, 1038.49),
(11, 7, 7, 1, 502.58),
(12, 7, 2, 1, 3381.39),
(13, 7, 3, 1, 514.55);

-- --------------------------------------------------------

--
-- Estrutura para tabela `pedido`
--

CREATE TABLE `pedido` (
  `codigo` int(11) NOT NULL,
  `cpfCliente` varchar(11) DEFAULT NULL,
  `dataPedido` date NOT NULL,
  `total` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `pedido`
--

INSERT INTO `pedido` (`codigo`, `cpfCliente`, `dataPedido`, `total`) VALUES
(4, '11111111111', '2024-11-29', 3883.97),
(5, '11111111111', '2024-11-29', 3504.41),
(6, '11111111111', '2024-11-29', 9394.52),
(7, '12345678900', '2024-11-30', 5437.01);

-- --------------------------------------------------------

--
-- Estrutura para tabela `produtos`
--

CREATE TABLE `produtos` (
  `codigo` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `preco` decimal(10,2) NOT NULL,
  `categoria` int(11) NOT NULL,
  `descricao` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `produtos`
--

INSERT INTO `produtos` (`codigo`, `nome`, `preco`, `categoria`, `descricao`) VALUES
(1, 'B550M AORUS ELITE', 379.81, 1, 'Placa-mãe com chipset B550, suporte a PCIe 4.0, ideal para CPUs Ryzen de 3ª geração, com design premium e ótimos recursos de conectividade.'),
(2, 'CORE I5 10600KF ', 3381.39, 2, 'Processador Intel Core i5 de 10ª geração, 6 núcleos e 12 threads, com alto desempenho e overclocking para jogos e tarefas exigentes.'),
(3, 'RTX 3060', 514.55, 3, 'Placa de vídeo NVIDIA com 12GB de memória GDDR6, ideal para jogos em 1080p e 1440p, oferecendo Ray Tracing e DLSS para gráficos incríveis.'),
(4, 'SANDISK SSD PLUS', 1038.49, 4, 'SSD de 480GB com velocidade de leitura e gravação de até 535MB/s, ideal para melhorar o desempenho do seu PC com armazenamento rápido e confiável.'),
(5, '16GB DDR4 2400MHZ KVR24S17D8/16 KINGSTON', 3124.60, 5, 'Memória RAM Kingston de 16GB, DDR4, com frequência de 2400MHz, oferecendo desempenho estável para multitarefas e jogos.'),
(6, 'CORSAIR RM', 1443.57, 6, 'Fonte de alimentação Corsair de 750W, certificação 80 Plus Gold, eficiente e confiável para alimentar sistemas de alto desempenho.'),
(7, 'FRACTAL DESIGN NODE', 502.58, 7, 'Gabinete compacto ATX da Fractal Design, com excelente ventilação, design minimalista e espaço para componentes poderosos.'),
(8, 'HYPERX ALLOY ORIGINS CORE', 1230.66, 8, 'Teclado mecânico HyperX com switches red, retroiluminação RGB, estrutura de alumínio e design compacto, ideal para gamers.'),
(9, 'DELL ALIENWARE AW610M', 2362.11, 9, 'Mouse gamer sem fio Alienware, com sensor de 16.000 DPI, design ergonômico e iluminação RGB personalizável para desempenho superior.'),
(10, 'ACER PREDATOR XBHU', 3803.49, 10, 'Monitor Acer Predator de 27 polegadas com resolução 4K, taxa de atualização de 144Hz e tecnologia G-Sync, ideal para jogos imersivos de alta performance.');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cpf`);

--
-- Índices de tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`cpf`);

--
-- Índices de tabela `itenspedido`
--
ALTER TABLE `itenspedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_PEDIDOS` (`pedidoId`),
  ADD KEY `FK_PRODUTOS` (`produtoId`);

--
-- Índices de tabela `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_CLIENTES` (`cpfCliente`);

--
-- Índices de tabela `produtos`
--
ALTER TABLE `produtos`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_CATEGORIAS` (`categoria`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `itenspedido`
--
ALTER TABLE `itenspedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de tabela `pedido`
--
ALTER TABLE `pedido`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `produtos`
--
ALTER TABLE `produtos`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `itenspedido`
--
ALTER TABLE `itenspedido`
  ADD CONSTRAINT `FK_PEDIDOS` FOREIGN KEY (`pedidoId`) REFERENCES `pedido` (`codigo`),
  ADD CONSTRAINT `FK_PRODUTOS` FOREIGN KEY (`produtoId`) REFERENCES `produtos` (`codigo`);

--
-- Restrições para tabelas `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `FK_CLIENTES` FOREIGN KEY (`cpfCliente`) REFERENCES `cliente` (`cpf`);

--
-- Restrições para tabelas `produtos`
--
ALTER TABLE `produtos`
  ADD CONSTRAINT `FK_CATEGORIAS` FOREIGN KEY (`categoria`) REFERENCES `categorias` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
