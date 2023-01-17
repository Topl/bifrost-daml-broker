package broker

import cats.effect.IO
import co.topl.daml.DamlAppContext
import co.topl.daml.ToplContext
import co.topl.daml.polys.processors.SignedTransferProcessor
import co.topl.daml.polys.processors.TransferRequestProcessor
import co.topl.daml.polys.processors.UnsignedTransferProcessor
import com.daml.ledger.javaapi.data.Transaction
import io.reactivex.Flowable

trait PolyProcessorRegistrationModule {

  def registerTransferProcessor(
  )(implicit
      transactions: Flowable[Transaction],
      damlAppContext: DamlAppContext,
      toplContext: ToplContext
  ) =
    for {
      transferProcessor <- IO(
        new TransferRequestProcessor(
          damlAppContext,
          toplContext,
          3000,
          (x, y) => true,
          t => true
        )
      )
      _ <- IO(transactions.forEach(transferProcessor.processTransaction))
    } yield ()

  def registerUnsignedTransferProcessor(
      keyfile: String,
      password: String
  )(implicit
      transactions: Flowable[Transaction],
      damlAppContext: DamlAppContext,
      toplContext: ToplContext
  ) =
    for {
      transferProcessor <- IO(
        new UnsignedTransferProcessor(
          damlAppContext,
          toplContext,
          keyfile,
          password
        )
      )
      _ <- IO(transactions.forEach(transferProcessor.processTransaction))
    } yield ()

  def registerSignedTransferProcessor(
  )(implicit
      transactions: Flowable[Transaction],
      damlAppContext: DamlAppContext,
      toplContext: ToplContext
  ) =
    for {
      transferProcessor <- IO(
        new SignedTransferProcessor(
          damlAppContext,
          toplContext,
          3000,
          1,
          (x, y) => true,
          t => true
        )
      )
      _ <- IO(transactions.forEach(transferProcessor.processTransaction))
    } yield ()
}
