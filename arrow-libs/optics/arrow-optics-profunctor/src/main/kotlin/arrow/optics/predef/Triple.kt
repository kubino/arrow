package arrow.optics.predef

import arrow.optics.AffineFoldK
import arrow.optics.AffineTraversalK
import arrow.optics.Fold
import arrow.optics.FoldK
import arrow.optics.GetterK
import arrow.optics.LensK
import arrow.optics.Optic
import arrow.optics.PLens
import arrow.optics.PTraversal
import arrow.optics.TraversalK
import arrow.optics.compose
import arrow.optics.internal.Applicative
import arrow.optics.internal.Kind
import arrow.optics.internal.WanderF
import arrow.optics.lens
import arrow.optics.traversing

fun <A, B, C, D> Optic.Companion.tripleFirst(): PLens<Triple<A, C, D>, Triple<B, C, D>, A, B> =
  Optic.lens({ (a, _, _) -> a }, { (_, c, d), b -> Triple(b, c, d) })

@JvmName("first_triple_lens")
fun <K : LensK, S, T, A, B, C, D> Optic<K, S, T, Triple<A, C, D>, Triple<B, C, D>>.first(): Optic<LensK, S, T, A, B> =
  compose(Optic.tripleFirst())

@JvmName("first_affine_traversal")
fun <K : AffineTraversalK, S, T, A, B, C, D> Optic<K, S, T, Triple<A, C, D>, Triple<B, C, D>>.first(): Optic<AffineTraversalK, S, T, A, B> =
  compose(Optic.tripleFirst())

@JvmName("first_triple_traversal")
fun <K : TraversalK, S, T, A, B, C, D> Optic<K, S, T, Triple<A, C, D>, Triple<B, C, D>>.first(): Optic<TraversalK, S, T, A, B> =
  compose(Optic.tripleFirst())

@JvmName("first_triple_getter")
fun <K : GetterK, S, T, A, B, C, D> Optic<K, S, T, Triple<A, C, D>, Triple<B, C, D>>.first(): Optic<GetterK, S, T, A, B> =
  compose(Optic.tripleFirst())

@JvmName("first_triple_affine_fold")
fun <K : AffineFoldK, S, T, A, B, C, D> Optic<K, S, T, Triple<A, C, D>, Triple<B, C, D>>.first(): Optic<AffineFoldK, S, T, A, B> =
  compose(Optic.tripleFirst())

@JvmName("first_triple_fold")
fun <K : FoldK, S, T, A, B, C, D> Optic<K, S, T, Triple<A, C, D>, Triple<B, C, D>>.first(): Optic<FoldK, S, T, A, B> =
  compose(Optic.tripleFirst())

fun <A, B, C, D> Optic.Companion.tripleSecond(): PLens<Triple<A, B, D>, Triple<A, C, D>, B, C> =
  Optic.lens({ (_, b, _) -> b }, { (a, _, d), c -> Triple(a, c, d) })

@JvmName("second_triple_lens")
fun <K : LensK, S, T, A, B, C, D> Optic<K, S, T, Triple<A, B, D>, Triple<A, C, D>>.second(): Optic<LensK, S, T, B, C> =
  compose(Optic.tripleSecond())

@JvmName("second_affine_traversal")
fun <K : AffineTraversalK, S, T, A, B, C, D> Optic<K, S, T, Triple<A, B, D>, Triple<A, C, D>>.second(): Optic<AffineTraversalK, S, T, B, C> =
  compose(Optic.tripleSecond())

@JvmName("second_triple_traversal")
fun <K : TraversalK, S, T, A, B, C, D> Optic<K, S, T, Triple<A, B, D>, Triple<A, C, D>>.second(): Optic<TraversalK, S, T, B, C> =
  compose(Optic.tripleSecond())

@JvmName("second_triple_getter")
fun <K : GetterK, S, T, A, B, C, D> Optic<K, S, T, Triple<A, B, D>, Triple<A, C, D>>.second(): Optic<GetterK, S, T, B, C> =
  compose(Optic.tripleSecond())

@JvmName("second_triple_affine_fold")
fun <K : AffineFoldK, S, T, A, B, C, D> Optic<K, S, T, Triple<A, B, D>, Triple<A, C, D>>.second(): Optic<AffineFoldK, S, T, B, C> =
  compose(Optic.tripleSecond())

@JvmName("second_triple_fold")
fun <K : FoldK, S, T, A, B, C, D> Optic<K, S, T, Triple<A, B, D>, Triple<A, C, D>>.second(): Optic<FoldK, S, T, B, C> =
  compose(Optic.tripleSecond())

fun <A, B, C, D> Optic.Companion.tripleThird(): PLens<Triple<A, B, C>, Triple<A, B, D>, C, D> =
  Optic.lens({ (_, _, c) -> c }, { (a, b, _), d -> Triple(a, b, d) })

@JvmName("third_triple_lens")
fun <K : LensK, S, T, A, B, C, D> Optic<K, S, T, Triple<A, B, C>, Triple<A, B, D>>.third(): Optic<LensK, S, T, C, D> =
  compose(Optic.tripleThird())

@JvmName("third_affine_traversal")
fun <K : AffineTraversalK, S, T, A, B, C, D> Optic<K, S, T, Triple<A, B, C>, Triple<A, B, D>>.third(): Optic<AffineTraversalK, S, T, C, D> =
  compose(Optic.tripleThird())

@JvmName("third_triple_traversal")
fun <K : TraversalK, S, T, A, B, C, D> Optic<K, S, T, Triple<A, B, C>, Triple<A, B, D>>.third(): Optic<TraversalK, S, T, C, D> =
  compose(Optic.tripleThird())

@JvmName("third_triple_getter")
fun <K : GetterK, S, T, A, B, C, D> Optic<K, S, T, Triple<A, B, C>, Triple<A, B, D>>.third(): Optic<GetterK, S, T, C, D> =
  compose(Optic.tripleThird())

@JvmName("third_triple_affine_fold")
fun <K : AffineFoldK, S, T, A, B, C, D> Optic<K, S, T, Triple<A, B, C>, Triple<A, B, D>>.third(): Optic<AffineFoldK, S, T, C, D> =
  compose(Optic.tripleThird())

@JvmName("third_triple_fold")
fun <K : FoldK, S, T, A, B, C, D> Optic<K, S, T, Triple<A, B, C>, Triple<A, B, D>>.third(): Optic<FoldK, S, T, C, D> =
  compose(Optic.tripleThird())

fun <A, B> Optic.Companion.tripleEvery(): PTraversal<Triple<A, A, A>, Triple<B, B, B>, A, B> =
  traversing(object : WanderF<Triple<A, A, A>, Triple<B, B, B>, A, B> {
    override fun <F> invoke(
      AF: Applicative<F>,
      source: Triple<A, A, A>,
      f: (A) -> Kind<F, B>
    ): Kind<F, Triple<B, B, B>> =
      AF.ap(
        AF.ap(
          AF.map(f(source.first)) { fst -> { snd: B -> { third: B -> Triple(fst, snd, third) } } },
          f(source.second)
        ),
        f(source.third)
      )
  })

@JvmName("triple_every_traversal")
fun <K : TraversalK, S, T, A, B> Optic<K, S, T, Triple<A, A, A>, Triple<B, B, B>>.every(): PTraversal<S, T, A, B> =
  compose(Optic.tripleEvery())

@JvmName("triple_every_fold")
fun <K : FoldK, S, T, A, B> Optic<K, S, T, Triple<A, A, A>, Triple<B, B, B>>.every(): Optic<FoldK, S, T, A, B> =
  compose(Optic.tripleEvery())
