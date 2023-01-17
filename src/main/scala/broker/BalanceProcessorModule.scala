package broker

import cats.effect.IO
import co.topl.daml.DamlAppContext
import co.topl.daml.ToplContext
import co.topl.daml.assets.processors.AssetBalanceRequestProcessor
import com.daml.ledger.javaapi.data.Transaction
import io.reactivex.Flowable

trait BalanceProcessorModule {

  def registerAssetBalanceProcessor()(implicit
      transactions: Flowable[Transaction],
      damlAppContext: DamlAppContext,
      toplContext: ToplContext
  ) =
    for {
      transferProcessor <- IO(
        new AssetBalanceRequestProcessor(
          damlAppContext,
          toplContext,
          3000,
          (x, y) => true,
          t => true
        )
      )
      _ <- IO(transactions.forEach(transferProcessor.processTransaction))
    } yield ()

}
