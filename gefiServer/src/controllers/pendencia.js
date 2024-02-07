const database = require('../models');
const validacoes = require('../utils/validador');
const { InvalidArgumentError, HttpException } = require('../utils/erros');

class PendenciaController {

  static async adiciona(req, res) {

    console.log(req.body)
    try {
      const pendencia={
        data_pendencia : new Date(),
        solucionada : false,
        controle_id : req.body.controle_id,
        motivo_pendencia : req.body.motivo_pendencia
      }
		
      const controle={
        id : req.body.controle_id,
        pendente : 1,
        status : "COM PENDÊNCIA"
      }

      const novaPendenciaCriada = await database.Pendencias.create(pendencia)
	    await database.Controles.update(controle,{
        where:{
          id:Number(controle.id)
        }
      });
      return res.status(200).json(novaPendenciaCriada)
    } catch (error) {
      return res.status(500).json(error.message)
    }
  }

    static async lista(req, res){
      try {
        const pendencias = await database.Pendencias.findAll();
        if (!pendencias.length) {
          throw new HttpException(404, 'Nenhuma Pendencia encontrada');
      }
        return res.status(200).json(pendencias)  
      } catch (error) {
        return res.status(500).json(error.message)
      }
    }

    static async listaPorPeriodo(req, res){
        const {dataInicio, dataFinal} = req.body;
      try {
        const pendencias = await database.Pendencias.findAll({
          where :{
            from: {
                $between: [dataInicio, dataFinal]
            }
          }
        });
        if (!pendencias.length) {
          throw new HttpException(404, 'Nenhuma Pendencia encontrada');
      }
        return res.status(200).json(pendencias)  
      } catch (error) {
        return res.status(500).json(error.message)
      }
    }

  

    static async buscaPorId(req, res) {
      const { id } = req.params
      try {
        const pendencia = await database.Pendencias.findOne( { 
          where: { 
            id: Number(id) 
          }
        });

        if(!pendencia){
          throw new HttpException(404, 'Pendencia não encontrada');
      }
        return res.status(200).json(pendencia)
      } catch (error) {
        return res.status(500).json(error.message)
      }
    }

    
    static async buscaUltimaPorControleId(req, res) {
      const { controle_id } = req.params
      try {
        const pendencia = await database.Pendencias.findOne( { 
          where: { 
            controle_id: Number(controle_id) 
          } 
        });

        if(!pendencia){
          throw new HttpException(404, 'Pendencia não encontrada');
      }
        return res.status(200).json(pendencia)
      } catch (error) {
        return res.status(500).json(error.message)
      }
    }


    static async atualiza(req, res) {
      const { id } = req.params
      console.log(req.body)
      try {
        const pendencia={
          id:id,
          data_solucao : new Date(),
          solucionada : true,
          controle_id : req.body.controle_id,
          solucao_pendencia : req.body.motivo_pendencia
        }
        const controle = {
          id : req.body.controle_id,
          pendente : 0,
          status : "SEM PENDÊNCIA"
        }

        await database.Pendencias.update(pendencia, { 
          where: { 
            id: Number(id)
        }});

       
        await database.Controles.update(controle,{
          where:{
            id:Number(controle.id)
          } 
        });
        const PendenciaAtualizada = await database.Pendencias.findOne( { where: { id: Number(id) }})
        return res.status(200).json(PendenciaAtualizada)
      } catch (error) {
        return res.status(500).json(error.message)
      }
    }


  static async deleta(req, res) {
    const { id } = req.params
    try {
      await database.Pendencias.destroy({ where: { id: Number(id) }})
      return res.status(200).json({ mensagem: `id ${id} deletado` })

    } catch (error) {
      return res.status(500).json(error.message)
    }
  }
}


module.exports = PendenciaController



