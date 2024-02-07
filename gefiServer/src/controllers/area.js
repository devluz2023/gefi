const database = require('../models');
const validacoes = require('../utils/validador');
const { InvalidArgumentError, HttpException } = require('../utils/erros');

class AreaController {

  static async adiciona(req, res) {
    const novaArea = req.body;  

    try {

      validacoes.campoStringNaoNulo(novaArea.descricao,'descricao');
      validacoes.campoStringNaoNulo(novaArea.sigla,'sigla');
      if (await AreaController.buscaPorSigla(novaArea.sigla)) {
        throw new InvalidArgumentError('A área já existe!');
      }
      const novaAreaCriada = await database.Areas.create(novaArea)
      return res.status(200).json(novaAreaCriada)
    } catch (error) {
      return res.status(500).json(error.message)
    }
  }

    static async buscaPorSigla(sigla) {
      try {
        const area = await database.Areas.findOne( { 
          where: { 
            sigla: sigla 
          }
        });
      
        return area;
      } catch (error) {
        return null;
      }
    }
 

    static async lista(req, res){
      
      try {
        const areas = await database.Areas.findAll();
        if (!areas.length) {
          throw new HttpException(404, 'Nenhuma área encontrada encontrado');
      }
        return res.status(200).json(areas)  
      } catch (error) {
        return res.status(500).json(error.message)
      }
    }

    static async buscaPorId(req, res) {
      const { id } = req.params
      try {
        const area = await database.Areas.findOne( { 
          where: { 
            id: Number(id) 
          }
        });

        if(!area){
          throw new HttpException(404, 'Área não encontrado');
      }
        return res.status(200).json(area)
      } catch (error) {
        return res.status(500).json(error.message)
      }
    }

    static async atualiza(req, res) {
      const { id } = req.params
      const novasInfos = req.body

      
      try {
       // validacoes.campoStringNaoNulo(novasInfos.descricao,'descricao');
        //validacoes.campoStringNaoNulo(novasInfos.sigla,'sigla');
        await database.Areas.update(novasInfos, { 
          where: { 
            id: Number(id)
          }})
        const AreaAtualizada = await database.Areas.findOne( { where: { id: Number(id) }})
        return res.status(200).json(AreaAtualizada)
      } catch (error) {
        return res.status(500).json(error.message)
      }
    }


  static async deleta(req, res) {
    const { id } = req.params
    try {
      await database.Areas.destroy({ where: { id: Number(id) }})
      return res.status(200).json({ mensagem: `id ${id} deletado` })

    } catch (error) {
      return res.status(500).json(error.message)
    }
  }
}


module.exports = AreaController



