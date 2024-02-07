const database = require('../models');
const validacoes = require('../utils/validador');
const { InvalidArgumentError, HttpException } = require('../utils/erros');

class SolicitacaoController {

  static async adiciona(req, res) {
	console.log(`chamando rota adiciona`);

    try {
		const solicitacao={
			data_solicitacao : new Date(),
			usuario_id : req.body.usuario.id,
			controle_id : req.body.controle.id,
			motivo_pendencia : req.body.motivo_pendencia
		}
		
		const controle={
			id : req.body.controle.id,
			disponivel : 0,
			status : "INDISPONIVEL"
		}
		
		console.log(solicitacao)
		console.log(controle)

	
      const novaSolicitacaoCriada = await database.Solicitacoes.create(solicitacao)
	  await database.Controles.update(controle,{
		  where:{
			  id:Number(controle.id)
		  }
	  });
	
        return res.status(200).json(novaSolicitacaoCriada)  
      } catch (error) {
        return res.status(500).json(error.message)
      }
    }

  
    static async listaPorUsuario(req, res){
      try {
        const solicitacoes = await database.Solicitacoes.findAll(
          {
            where: { 
              usuario_if: Number(usuario_id) 
            }
          }
        );
        if (!solicitacoes.length) {
          throw new HttpException(404, 'Nenhuma Solicitacao encontrada');
      }
        return res.status(200).json(solicitacoes)  
      } catch (error) {
        return res.status(500).json(error.message)
      }
    }
	
	
	 static async lista(req, res){
      try {
        const solicitacoes = await database.Solicitacoes.findAll();
        if (!solicitacoes.length) {
          throw new HttpException(404, 'Nenhuma Solicitacao encontrada');
      }
        return res.status(200).json(solicitacoes)  
      } catch (error) {
        return res.status(500).json(error.message)
      }
    }

    static async buscaPorId(req, res) {
      const { id } = req.params
      try {
        const solicitacao = await database.Solicitacoes.findOne( { 
          where: { 
            id: Number(id) 
          }
        });

        if(!solicitacao){
          throw new HttpException(404, 'Solicitacao não encontrada');
      }
        return res.status(200).json(solicitacao)
      } catch (error) {
        return res.status(500).json(error.message)
      }
    }


    static async buscaUltimaPorControle(req, res) {
      const { controle_id } = req.params
      try {
        const solicitacao = await database.Solicitacoes.findOne( { 
          where: { 
            controle_id: Number(controle_id) 
          },
          include: [
            { model: database.Usuarios, as: "usuario" }
          ]}
        );

        if(!solicitacao){
          throw new HttpException(404, 'Solicitacao não encontrada');
      }
        return res.status(200).json(solicitacao)
      } catch (error) {
        return res.status(500).json(error.message)
      }
    }


    

    static async atualiza(req, res) {
      const { id } = req.params
	  
      try {
		  const solicitacao= {
			data_devolucao : new Date()
		}
		
        await database.Solicitacoes.update(solicitacao, { 
          where: { 
            id: Number(id)
          }});
		  
		const controle={
			id : req.body.controle_id,
			disponivel : 1,
			status : "DISPONIVEL"
		}
		
		await database.Controles.update(controle,{
			where:{
				id:Number(controle.id)
			}
		});
		  
        const solicitacaoAtualizada = await database.Solicitacoes.findOne( { where: { id: Number(id) }})
        return res.status(200).json(solicitacaoAtualizada)
      } catch (error) {
        return res.status(500).json(error.message)
      }
    }


  static async deleta(req, res) {
    const { id } = req.params
    try {
      await database.Solicitacoes.destroy({ where: { id: Number(id) }})
      return res.status(200).json({ mensagem: `id ${id} deletado` })

    } catch (error) {
      return res.status(500).json(error.message)
    }
  }
}


module.exports = SolicitacaoController



