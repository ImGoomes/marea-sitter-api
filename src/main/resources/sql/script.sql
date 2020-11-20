
-- CREATE TABLE ACAO
CREATE TABLE ACAO
    ( acao_id      NUMBER
       GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1)
       CONSTRAINT  acao_id_nn NOT NULL
    , nome    VARCHAR2(40) NOT NULL
    , descricao    VARCHAR2(100) NOT NULL
    , ativo   NUMBER(1,0) NOT NULL
    );

CREATE UNIQUE INDEX acao_id_pk
ON ACAO (acao_id);

ALTER TABLE ACAO
ADD ( CONSTRAINT acao_id_pk
       		 PRIMARY KEY (acao_id)
    ) ;

CREATE TABLE EXECUCAO
    ( execucao_id      NUMBER
       GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1)
       CONSTRAINT  execucao_id_nn NOT NULL
    , acao_id      NUMBER NOT NULL
    , data_execucao DATE NOT NULL
    );

CREATE UNIQUE INDEX execucao_id_pk
ON EXECUCAO (execucao_id);

ALTER TABLE EXECUCAO
ADD ( CONSTRAINT execucao_id_pk
       		 PRIMARY KEY (execucao_id)
    , CONSTRAINT acao_c_id_fk
       		 FOREIGN KEY (acao_id)
        	  REFERENCES ACAO(acao_id)
    ) ;