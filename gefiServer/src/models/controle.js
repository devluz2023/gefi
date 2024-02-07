'use strict';
module.exports = (sequelize, DataTypes) => {
  const Controle = sequelize.define('Controles', {
    disponivel: DataTypes.BOOLEAN,
    coletivo: DataTypes.BOOLEAN,
    pendente: DataTypes.BOOLEAN,
    status:DataTypes.STRING
  }, {});
  Controle.associate = function(models) {
    // associations can be defined here
    Controle.belongsTo(models.Usuarios, {
      as:'usuario',
      foreignKey: 'usuario_id',
      targetKey: 'id'
    });

    Controle.belongsTo(models.Areas, {
      as:'area',
      foreignKey: 'area_id', 
      targetKey: 'id'
    });

    Controle.belongsTo(models.Equipamentos, {
      as:'equipamento',
      foreignKey: 'equipamento_id', 
      targetKey: 'id'
    });

    Controle.hasMany(models.Pendencias, {
      foreignKey: 'controle_id', 
      sourceKey: 'id'
    });

    Controle.hasMany(models.Solicitacoes, {
       foreignKey: 'controle_id',
       sourceKey: 'id'
      });
   
   
  };
  return Controle;
};