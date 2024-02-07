'use strict';
module.exports = (sequelize, DataTypes) => {
  const Solicitacao = sequelize.define('Solicitacoes', {
    data_solicitacao: DataTypes.DATE,
    data_devolucao: DataTypes.DATE,
  }, {});
  Solicitacao.associate = function(models) {
    // associations can be defined here
    Solicitacao.belongsTo(models.Controles, {
      foreignKey: 'controle_id',
      targetKey: 'id'
    });

    Solicitacao.belongsTo(models.Usuarios,{
      as:'usuario',
      foreignKey: 'usuario_id',
      targetKey: 'id'
    });
  

  };
  
  return Solicitacao;
};