'use strict';
module.exports = {
  up: (queryInterface, Sequelize) => {
    return queryInterface.createTable('Solicitacoes', {
      id: {
        allowNull: false,
        autoIncrement: true,
        primaryKey: true,
        type: Sequelize.INTEGER
      },
      data_solicitacao: {
        type: Sequelize.DATE
      },
      data_devolucao: {
        type: Sequelize.DATE
      },
  
      controle_id: {
        allowNull: false,
        type: Sequelize.INTEGER,
        references: {
           model: 'Controles', 
           key: 'id'
        }
      },
      usuario_id: {
        allowNull: false,
        type: Sequelize.INTEGER,
        references: {
           model: 'Usuarios', 
           key: 'id'
        }
      },
      createdAt: {
        allowNull: false,
        type: Sequelize.DATE
      },
      updatedAt: {
        allowNull: false,
        type: Sequelize.DATE
      }
    });
  },
  down: (queryInterface, Sequelize) => {
    return queryInterface.dropTable('Solicitacaos');
  }
};