package broker

import cats.effect.IO
import co.topl.daml.DamlAppContext
import co.topl.daml.ToplContext
import co.topl.daml.assets.processors.AssetTransferRequestEd25519Processor
import co.topl.daml.assets.processors.SignedAssetTransferRequestProcessor
import com.daml.ledger.javaapi.data.Transaction
import io.reactivex.Flowable

trait AssetTransferProcessorRegistrationModule {

  def registerAssetTransferRequestProcessor()(implicit
      transactions: Flowable[Transaction],
      damlAppContext: DamlAppContext,
      toplContext: ToplContext
  ) =
    for {
      transferProcessor <- IO(
        new AssetTransferRequestEd25519Processor(
          damlAppContext,
          toplContext,
          3000,
          (x, y) => true,
          t => true
        )
      )
      _ <- IO(transactions.forEach(transferProcessor.processTransaction))
    } yield ()

  def registerSignedAssetTransferRequestProcessor()(implicit
      transactions: Flowable[Transaction],
      damlAppContext: DamlAppContext,
      toplContext: ToplContext
  ) = {
    var i = 0
    for {
      transferProcessor <- IO(
        new SignedAssetTransferRequestProcessor(
          damlAppContext,
          toplContext,
          3000,
          1,
          () => {
            i = i + 1
            i.toString()
          },
          (x, y) => true,
          t => true
        )
      )
      _ <- IO(transactions.forEach(transferProcessor.processTransaction))
    } yield ()
  }
}
