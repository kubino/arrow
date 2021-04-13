package arrow.optics.predef

import arrow.core.Either
import arrow.optics.AffineFoldK
import arrow.optics.AffineTraversalK
import arrow.optics.Fold
import arrow.optics.FoldF
import arrow.optics.FoldK
import arrow.optics.IsoK
import arrow.optics.Optic
import arrow.optics.PAffineTraversal
import arrow.optics.PPrism
import arrow.optics.PTraversal
import arrow.optics.PrismK
import arrow.optics.TraversalK
import arrow.optics.compose
import arrow.optics.folding
import arrow.optics.internal.Applicative
import arrow.optics.internal.Kind
import arrow.optics.internal.WanderF
import arrow.optics.prism
import arrow.optics.traverseOf
import arrow.optics.traverseOf_
import arrow.optics.traversing

fun <A, B, C> Optic.Companion.eitherLeft(): PPrism<Either<A, C>, Either<B, C>, A, B> =
  Optic.prism({ e ->
    e.fold({ a -> Either.Right(a) }, { c -> Either.Left(Either.Right(c)) })
  }, { b -> Either.Left(b) })

@JvmName("either_left_prism")
fun <K : PrismK, S, T, A, B, C> Optic<K, S, T, Either<A, C>, Either<B, C>>.left(): Optic<PrismK, S, T, A, B> =
  compose(Optic.eitherLeft())

@JvmName("either_left_affineTraversal")
fun <K : AffineTraversalK, S, T, A, B, C> Optic<K, S, T, Either<A, C>, Either<B, C>>.left(): Optic<AffineTraversalK, S, T, A, B> =
  compose(Optic.eitherLeft())

@JvmName("either_left_traversal")
fun <K : TraversalK, S, T, A, B, C> Optic<K, S, T, Either<A, C>, Either<B, C>>.left(): Optic<TraversalK, S, T, A, B> =
  compose(Optic.eitherLeft())

@JvmName("either_left_affineFold")
fun <K : AffineFoldK, S, T, A, B, C> Optic<K, S, T, Either<A, C>, Either<B, C>>.left(): Optic<AffineFoldK, S, T, A, B> =
  compose(Optic.eitherLeft())

@JvmName("either_left_fold")
fun <K : FoldK, S, T, A, B, C> Optic<K, S, T, Either<A, C>, Either<B, C>>.left(): Optic<FoldK, S, T, A, B> =
  compose(Optic.eitherLeft())

fun <A, B, C> Optic.Companion.eitherRight(): PPrism<Either<A, B>, Either<A, C>, B, C> =
  Optic.prism({ e ->
    e.fold({ a -> Either.Left(Either.Left(a)) }, { b -> Either.Right(b) })
  }, { c -> Either.Right(c) })

@JvmName("either_right_prism")
fun <K : PrismK, S, T, A, B, C> Optic<K, S, T, Either<A, B>, Either<A, C>>.right(): Optic<PrismK, S, T, B, C> =
  compose(Optic.eitherRight())

@JvmName("either_right_affineTraversal")
fun <K : AffineTraversalK, S, T, A, B, C> Optic<K, S, T, Either<A, B>, Either<A, C>>.right(): Optic<AffineTraversalK, S, T, B, C> =
  compose(Optic.eitherRight())

@JvmName("either_right_traversal")
fun <K : TraversalK, S, T, A, B, C> Optic<K, S, T, Either<A, B>, Either<A, C>>.right(): Optic<TraversalK, S, T, B, C> =
  compose(Optic.eitherRight())

@JvmName("either_right_affineFold")
fun <K : AffineFoldK, S, T, A, B, C> Optic<K, S, T, Either<A, B>, Either<A, C>>.right(): Optic<AffineFoldK, S, T, B, C> =
  compose(Optic.eitherRight())

@JvmName("either_right_fold")
fun <K : FoldK, S, T, A, B, C> Optic<K, S, T, Either<A, B>, Either<A, C>>.right(): Optic<FoldK, S, T, B, C> =
  compose(Optic.eitherRight())

fun <A, B> Optic.Companion.eitherEvery(): PTraversal<Either<A, A>, Either<B, B>, A, B> =
  traversing(object : WanderF<Either<A, A>, Either<B, B>, A, B> {
    override fun <F> invoke(AF: Applicative<F>, source: Either<A, A>, f: (A) -> Kind<F, B>): Kind<F, Either<B, B>> =
      source.fold({ a ->
        AF.map(f(a)) { b -> Either.Left(b) }
      }, { a ->
        AF.map(f(a)) { b -> Either.Right(b) }
      })
  })

@JvmName("either_every_traversal")
fun <K : TraversalK, S, T, A, B> Optic<K, S, T, Either<A, A>, Either<B, B>>.every(): PTraversal<S, T, A, B> =
  compose(Optic.eitherEvery())

@JvmName("either_every_fold")
fun <K : FoldK, S, A> Optic<K, S, S, Either<A, A>, Either<A, A>>.every(): Fold<S, A> =
  compose(Optic.eitherEvery())

@JvmName("either_beside_traversal")
fun <K1 : TraversalK, K2 : TraversalK, A, B, C, D, E, F> Optic.Companion.eitherBeside(
  l: Optic<K1, A, C, E, F>,
  r: Optic<K2, B, D, E, F>
): PTraversal<Either<A, B>, Either<C, D>, E, F> =
  traversing(object : WanderF<Either<A, B>, Either<C, D>, E, F> {
    override fun <G> invoke(AF: Applicative<G>, source: Either<A, B>, f: (E) -> Kind<G, F>): Kind<G, Either<C, D>> =
      source.fold({ a ->
        AF.map(a.traverseOf(l, AF, f)) { c -> Either.Left(c) }
      }, { b ->
        AF.map(b.traverseOf(r, AF, f)) { d -> Either.Right(d) }
      })
  })

@JvmName("either_beside_fold")
fun <K1 : FoldK, K2 : FoldK, A, B, C> Optic.Companion.eitherBeside(
  l: Optic<K1, A, A, C, C>,
  r: Optic<K2, B, B, C, C>
): Fold<Either<A, B>, C> =
  folding(object : FoldF<Either<A, B>, C> {
    override fun <F> invoke(AF: Applicative<F>, s: Either<A, B>, f: (C) -> Kind<F, Unit>): Kind<F, Unit> =
      s.fold({ a ->
        a.traverseOf_(l, AF, f)
      }, { b ->
        b.traverseOf_(r, AF, f)
      })
  })

@JvmName("either_beside_traversal")
fun <K1 : TraversalK, K2 : TraversalK, K3: TraversalK, S, T, A, B, C, D, E, F> Optic<K1, S, T, Either<A, B>, Either<C, D>>.beside(
  l: Optic<K2, A, C, E, F>,
  r: Optic<K3, B, D, E, F>
): PTraversal<S, T, E, F> =
  compose(Optic.eitherBeside(l, r))

@JvmName("either_beside_fold")
fun <K1 : FoldK, K2 : FoldK, K3: FoldK, S, A, B, C> Optic<K1, S, S, Either<A, B>, Either<A, B>>.beside(
  l: Optic<K2, A, A, C, C>,
  r: Optic<K3, B, B, C, C>
): Fold<S, C> =
  compose(Optic.eitherBeside(l, r))
