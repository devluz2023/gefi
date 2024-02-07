const Router = require('express');
const solicitacaoContoller = require('../controllers/solicitacao');
const router = Router()

const rota = `/solicitacoes`;
router.post(rota, solicitacaoContoller.adiciona);
router.get(rota, solicitacaoContoller.lista);
router.get(`${rota}/:id`, solicitacaoContoller.buscaPorId);
router.get(`${rota}/usuario/:id`, solicitacaoContoller.listaPorUsuario);
router.patch(`${rota}/:id`, solicitacaoContoller.atualiza);
router.get(`${rota}/controle/:controle_id`, solicitacaoContoller.buscaUltimaPorControle);
router.delete(`${rota}/:id`, solicitacaoContoller.deleta);
module.exports = router;


