'use strict';
module.exports = (sequelize, DataTypes) => {
  const Equipamento = sequelize.define('Equipamentos', {
    descricao: DataTypes.STRING,
    codigo_cptm: DataTypes.STRING,
    fabricante: DataTypes.STRING,
    modelo: DataTypes.STRING,
    ativo: DataTypes.BOOLEAN
  }, {});
  Equipamento.associate = function(models) {
    // associations can be defined here

    Equipamento.hasOne(models.Controles, {foreignKey: 'equipamento_id', sourceKey: 'id'});
   
  };
  return Equipamento;
};