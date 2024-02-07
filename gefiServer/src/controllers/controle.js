const database = require('../models');
const validacoes = require('../utils/validador');
const { InvalidArgumentError, HttpException } = require('../utils/erros');

class ControleController {

  static async adiciona(req, res) {
    const novoControle = req.body;
	console.log(novoControle)
	console.log("rota ocntrole adicionar")
    try {
	
		const controle = {
				usuario_id : novoControle.usuario.id,
				equipamento_id : novoControle.equipamento.id,
				area_id:novoControle.usuario.area_id,
				coletivo:novoControle.coletivo
		}
	
		if(novoControle.coletivo==1){
			controle.disponivel = true,
			controle.status = "DISPONIVEL"
		}else if(novoControle.coletivo==0){
			controle.pendente = false,
			controle.status = "SEM PENDENCIA"
		}
		
		console.log(controle)
		
      const novoControleCriada = await database.Controles.create(controle)
      return res.status(200).json(novoControleCriada)
    } catch (error) {
      return res.status(500).json(error.message)
    }
  }

    static async lista(req, res){
      const {coletivo, role, usuario_id, area_id} = req.params;

      var coletivo_boolean = 0;
      const array={};

      array.coletivo = coletivo_boolean
      if(coletivo=='true'){
        coletivo_boolean=1;
        array.coletivo = coletivo_boolean
      } else if(coletivo=='false'){
         if(role ==='USER'){
          array.usuario_id = usuario_id;
        }else if(role==='GESTOR'){
          array.area_id =area_id
        }
      }


      try {
        
        const controles = await database.Controles.findAll({
          where:array, include: [
            { model: database.Areas, as: "area" },
            { model: database.Usuarios, as: "usuario" },
            { model: database.Equipamentos, as: "equipamento" }
          ]}
        );


        if (!controles.length) {
          throw new HttpException(404, 'Nenhuma controle encontrada encontrado');
      }
        return res.status(200).json(controles)  
      } catch (error) {
        return res.status(500).json(error.message)
      }
    }

    static async buscaPorId(req, res) {
      const { id } = req.params
      try {
    
        const controle = await database.Controles.findAll( { 
          where: { 
            id: Number(id) ,
          }, include: [
            { model: database.Areas, as: "area" },
            { model: database.Usuarios, as: "usuario" },
            { model: database.Equipamentos, as: "equipamento" }
          ]

        });

        if (!controle) {
          throw new HttpException(404, 'controle não encontrado');
      }
        return res.status(200).json(controle)
      } catch (error) {
        return res.status(500).json(error.message)
      }
    }

    static async listaPorUsuario(req, res) {
      const { usuario_id } = req.params;
      console.log(req.params)
      try {
        const controles = await database.Controles.findAll( { 
          where: { 
            usuario_id: Number(usuario_id) 
          },
          include: [database.Areas, database.Equipamentos, database.Usuarios]
          
        });

        if (!controles.length) {
          throw new HttpException(404, 'Controle não encontrada');
      }
        return res.status(200).json(controles)
      } catch (error) {
        return res.status(500).json(error.message)
      }
    }


    static async listaPorArea(req, res) {
      const { area_id } = req.params
      try {
        const controles = await database.Controles.findAll( { 
          where: { 
            area_id: parseInt(area_id)
          },
          include: [database.Areas, database.Equipamentos, database.Usuarios]
        });

        if (!controles.length) {
          throw new HttpException(404, 'Controle não encontrada');
      }
        return res.status(200).json(controles)
      } catch (error) {
        return res.status(500).json(error.message)
      }
    }


    static async atualiza(req, res) {
      const { id } = req.params
      const novosInfos = req.body;
      try {
        await database.Controles.update(novosInfos, { 
          where: { 
            id: Number(id)
          }});

        const ControleAtualizada = await database.Controles.findOne( { where: { id: Number(id) }})
        return res.status(200).json(ControleAtualizada)
      } catch (error) {
        return res.status(500).json(error.message)
      }
    }


  static async deleta(req, res) {
    const { id } = req.params
    try {
      await database.Controles.destroy({ where: { id: Number(id) }})
      return res.status(200).json({ mensagem: `id ${id} deletado` })

    } catch (error) {
      return res.status(500).json(error.message)
    }
  }
}


module.exports = ControleController



