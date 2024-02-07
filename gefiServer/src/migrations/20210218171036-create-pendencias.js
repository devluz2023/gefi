'use strict';
module.exports = {
  up: (queryInterface, Sequelize) => {
    return queryInterface.createTable('Pendencias', {
      id: {
        allowNull: false,
        autoIncrement: true,
        primaryKey: true,
        type: Sequelize.INTEGER
      },
      motivo_pendencia: {
        type: Sequelize.STRING
      },
      solucao_pendencia: {
        type: Sequelize.STRING
      },
      data_pendencia: {
        type: Sequelize.DATE
      },
      data_solucao: {
        type: Sequelize.DATE
      },
      solucionada:{
        type:Sequelize.BOOLEAN
      },
      controle_id: {
        allowNull: false,
        type: Sequelize.INTEGER,
        references: {
           model: 'Controles', 
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
    return queryInterface.dropTable('Pendencias');
  }
};