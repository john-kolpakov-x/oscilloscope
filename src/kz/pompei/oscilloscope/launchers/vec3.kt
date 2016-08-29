package kz.pompei.oscilloscope.launchers

class vec3(var x: Double = 0.0, var y: Double = 0.0, var z: Double = 0.0) {

  fun get(i: Int): Double {
    return when (i) {
      0 -> x ; 1 -> y ; 2 -> z
      else -> throw IllegalArgumentException("i = " + i);
    }
  }

  var xy: vec2
    get() = vec2(x, y)
    set(v) {
      x = v.x
      y = v.y
    }

  var yz: vec2
    get() = vec2(y, z)
    set(v) {
      y = v.x
      z = v.y
    }

  var zx: vec2
    get() = vec2(z, x)
    set(v) {
      z = v.x
      x = v.y
    }

  operator fun plus(a: vec3): vec3 {
    return vec3(x + a.x, y + a.y, z + a.z)
  }

  operator fun minus(a: vec3): vec3 {
    return vec3(x - a.x, y - a.y, z - a.z)
  }

  override fun toString(): String {
    return "vec3($x, $y, $z)"
  }
}
