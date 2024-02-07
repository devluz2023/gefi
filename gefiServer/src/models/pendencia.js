'use strict';
module.exports = (sequelize, DataTypes) => {
  const Pendencias = sequelize.define('Pendencias', {
    motivo_pendencia: DataTypes.STRING,
    solucao_pendencia: DataTypes.STRING,
    data_pendencia: DataTypes.DATE,
    data_solucao: DataTypes.DATE,
    solucionada: DataTypes.BOOLEAN
  }, {});
  Pendencias.associate = function(models) {
    // associations can be defined here
    Pendencias.belongsTo(models.Controles, {
      as:'controle',
      foreignKey: 'controle_id',
      targetKey: 'id',
    });
    
  };
  return Pendencias;
};